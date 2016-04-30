#!/usr/local/bin/python

import os
import sys
import json
import time
from pyjavaproperties import Properties

settings_file_name = "settings.properties"
server_folder = "~/jars/"
# Dont Change
property_file_name = "config.properties"
configHostname = "configServerHostName"
configPortname = "configServerPort"

info = []
hosts = []
hosts_up = []
jar_info = []
servers = []
clients = []
server_machines = {}

def main():
    ping()
    clean()
    build()
    run()
    print "Waiting for simulation to finish..."
    f = finished()
    while True:
        if f:
            break;
        f = finished()
        time.sleep(1)
    print "Simulation has finished."
    get_log()
    clean()

def ping():
    print "Pinging servers..."
    get_hosts()
    for i in hosts:
        response = os.system("ping -c 1 " + i[0] + " > /dev/null 2>&1")
        if response != 0:
            print i[0] + ', is down!'
        else:
            hosts_up.append(i)
    print "Finished scanning for active servers, " + str(len(hosts_up)) + " active servers."

def build():
    print "Creating jar files for all servers and clients..."
    get_jar_info()
    for i in jar_info:
        status = create_jars(i["name"], i["executable_class"])
        if status == 0:
            print "Created jar file for " + i["name"]
        else:
            print "Error creating jar for " + i["name"]
    print "Done creating jar files."

def run():
    if hosts_up == []:
        ping()
    get_jar_info()
    create_folder()

    new_properties()
    if len(hosts_up) > len(servers):
        for i in range(0,len(servers)):
            changePropertie(str(servers[i][1]), str(hosts_up[i][0]))
            server_machines[servers[i][0]] = hosts_up[i]
        copy_properties()
        copy_jars()

        run_servers()
        run_clients()

    else:
        # Handle less than 6 machines where servers and players have to co-exist
        print "Error not enough machines to run servers and clients in different machines"
        sys.exit()

def run_servers():
    print "Running Servers..."
    p = Properties()
    p.load(open(property_file_name, 'r'))
    hostname = p[configHostname]
    port = p[configPortname]

    status = ssh(server_machines["ConfigServer"][0], server_machines["ConfigServer"][1], "cd " + server_folder + " && java -jar " + server_folder + "ConfigServer.jar")
    if status != 0:
            print "Error running ConfigServer."
    print "Started ConfigServer on " + server_machines["ConfigServer"][0] + "..."
    status = ssh(server_machines["ConfigServer"][0], server_machines["ConfigServer"][1], "cd " + server_folder + " && ps aux | grep '[j]ava -jar /home' | grep " + server_machines["ConfigServer"][1] + " | awk '{print \$2}' > pid", blocking=True, verbose=True)
    if status != 0:
            print "Error saving pid file."

    time.sleep(1)
    for i in server_machines:
        if i == "ConfigServer":
            continue
        status = ssh(server_machines[i][0],server_machines[i][1], "cd " + server_folder + " && java -jar " + server_folder + i + ".jar " + hostname + " " + port)
        if status != 0:
            print "Error running " + i + "."
        else:
            print "Started " + i + " on " + server_machines[i][0] + "..."
        status = ssh(server_machines[i][0],server_machines[i][1], "cd " + server_folder + " && ps aux | grep '[j]ava -jar /home' | grep " + server_machines[i][1] + " | awk '{print \$2}' > pid", blocking=True, verbose=True)
        if status != 0:
            print "Error saving pid file."
    time.sleep(2)

def run_clients():
    p = Properties()
    p.load(open(property_file_name, 'r'))
    hostname = p[configHostname]
    port = p[configPortname]

    total_players = int(p["nTeamPlayers"])
    total_coaches = int(p["nCoaches"])

    nRef = 0
    nCoach = 0
    nPlayerA = 0
    nPlayerB = 0
    teamCoach = "A"

    while ((nPlayerA != total_players) or (nPlayerB != total_players) or (nCoach != total_coaches) or (nRef != 1)):
        for i in range(len(servers), len(hosts_up)):
            if nRef < 1:
                status = ssh(hosts_up[i][0],hosts_up[i][1], "cd " + server_folder + " && java -jar " + server_folder + clients[2] + ".jar " + hostname + " " + port)
                if status != 0:
                    print "Error running " + clients[2] + "."
                else:
                    print "Started " + clients[2] + " on " + hosts_up[i][0] + "..."
                nRef = nRef + 1

            elif nCoach < total_coaches:
                status = ssh(hosts_up[i][0],hosts_up[i][1], "cd " + server_folder + " && java -jar " + server_folder + clients[1] + ".jar " + teamCoach + " " + hostname + " " + port)
                if status != 0:
                    print "Error running " + clients[1] + "."
                else:
                    print "Started " + clients[1] + " on " + hosts_up[i][0] + "..."
                if teamCoach == "A":
                    teamCoach = "B"
                nCoach = nCoach + 1

            elif nPlayerA < total_players:
                status = ssh(hosts_up[i][0],hosts_up[i][1], "cd " + server_folder + " && java -jar " + server_folder + clients[0] + ".jar " + str(nPlayerA) + " A " + hostname + " " + port)
                if status != 0:
                    print "Error running " + clients[0] + "."
                else:
                    print "Started " + clients[0] + " on " + hosts_up[i][0] + "..."
                nPlayerA = nPlayerA + 1

            elif nPlayerB < total_players:
                status = ssh(hosts_up[i][0],hosts_up[i][1], "cd " + server_folder + " && java -jar " + server_folder + clients[0] + ".jar " + str(nPlayerB) + " B " + hostname + " " + port)
                if status != 0:
                    print "Error running " + clients[0] + "."
                else:
                    print "Started " + clients[0] + " on " + hosts_up[i][0] + "..."
                nPlayerB = nPlayerB + 1

            status = ssh(hosts_up[i][0],hosts_up[i][1], "cd " + server_folder + " && ps aux | grep '[j]ava -jar /home' | grep " + hosts_up[i][1] + " | awk '{print \$2}' > pid", blocking=True, verbose=True)
            if status != 0:
                print "Error saving pid file."
            time.sleep(0.1)

def get_log(log_name="./"):
    if hosts_up == []:
        ping()
    print "Getting log file..."
    scp(hosts_up[4][0], hosts_up[4][1], log_name, inv_cp=True, server_file="GameOfTheRope*")
    print "Log file saved."

def finished():
    if hosts_up == []:
        ping()
    for i in hosts_up:
        status = ssh(i[0],i[1], "kill -0 \$(cat " + server_folder + "pid)", blocking=True, verbose=False)
        if status == 0:
            return False
    return True

def check_finished():
    print "Checking if simulation has ended..."
    f = finished()
    if f:
        print "Simulation has finished!"
    else:
        print "Simulation still running"

def kill():
    if hosts_up == []:
        ping()
    print "Killing all processes in all machines..."
    for i in hosts_up:
        status = ssh(i[0],i[1], "kill \$(cat " + server_folder + "pid)", blocking=True, verbose=False)
    print "Finished killing all processes."

def create_folder():
    print "Creating remote folders for jar execution..."
    for i in hosts_up:
        status = ssh(i[0], i[1], "mkdir " + server_folder)
        if status != 0:
            print "Error creating folder in " + i[0] + ", already exists."

def copy_jars():
    print "Copying jar file to machines..."
    for i in range(0,len(servers)):
        status = scp(hosts_up[i][0],hosts_up[i][1],"../jars/" + servers[i][0] + ".jar")
        if status != 0:
            print "Error copying file to " + hosts_up[i][0] + ", already exists"

    for i in range(len(servers), len(hosts_up)):
        for j in clients:
            status = scp(hosts_up[i][0],hosts_up[i][1],"../jars/" + j + ".jar")
            if status != 0:
                print "Error copying file to " + hosts_up[i][0] + ", already exists"

    print "Copied jars sucessfully!"

def copy_properties():
    print "Copying settings file to machine..."

    status = scp(server_machines["ConfigServer"][0],server_machines["ConfigServer"][1],property_file_name)
    if status != 0:
        print "Error copying file to " + i[0] + ", already exists"
    print "Copied settings file sucessfully!"


def new_properties():
    open(property_file_name, 'w+').write(open(settings_file_name, 'r').read())

def changePropertie(name, new_value):
    p = Properties()
    p.load(open(property_file_name, 'r'))
    #p.list()
    p[name] = new_value
    p.store(open(property_file_name,'w'))

def create_jars(name, executable):
    os.chdir("../build/classes")
    response = os.system("jar cvfe ../../jars/" + name + ".jar " + executable + " * > /dev/null 2>&1")
    os.chdir("../../scripts")
    return response

def ssh(server, username, cmd, blocking=False, verbose=False):
    block = "&"
    output = " > /dev/null 2>&1"
    if blocking:
        block = ""
    if verbose:
        output = ""
    #print "ssh " + username + "@" + server + " \"" + cmd + output + "\" " + block
    return os.system("ssh " + username + "@" + server + " \"" + cmd + output + "\" " + block)

def scp(server, username, file, inv_cp=False, server_file=""):
    if inv_cp:
        return os.system("scp " + username + "@" + server + ":" + server_folder + server_file + " " + file + " > /dev/null 2>&1")
    return os.system("scp " + file + " " + username + "@" + server + ":" + server_folder + " > /dev/null 2>&1")

def get_jar_info():
    global jar_info
    global servers
    global clients
    file = open("build.json", 'r').read()
    j = json.loads(file)
    jar_info = j["build-targets"]
    servers = [(i["name"],i["config"]) for i in jar_info if i["type"] == "Server"]
    clients = [i["name"] for i in jar_info if i["type"] == "Client"]

def get_hosts():
    global hosts
    file = open("hosts.json", 'r').read()
    j = json.loads(file)
    info = j["hosts"]
    hosts = [(host["hostname"], host["user"]) for host in info]

def clean():
    if hosts_up == []:
        ping()
    print "Cleaning up machines..."
    for i in hosts_up:
        status = ssh(i[0], i[1], "rm -rf " + server_folder)
        if status != 0:
            print "Error cleaning machine " + i[0] + "."
    print "Finished cleaning machines."

if __name__ == '__main__':
    if len(sys.argv) > 1:
        if sys.argv[1] == "ping":
            ping()
        elif sys.argv[1] == "build":
            build()
        elif sys.argv[1] == "run":
            run()
        elif sys.argv[1] == "clean":
            clean()
        elif sys.argv[1] == "finished":
            check_finished()
        elif sys.argv[1] == "kill":
            kill()
        elif sys.argv[1] == "log":
            if (len(sys.argv) > 2) and (sys.argv[2] == "-n"):
                if (len(sys.argv) > 3):
                    if (sys.argv[3] == None):
                        print "Log name after '-n' is required"
                        sys.exit()
                    get_log(sys.argv[3])
                    sys.exit()
            get_log()
        elif sys.argv[1] == "help":
            print "Usage: ./SD_script.py ping | build | run | clean | finished | kill | log -n log_name | help"
            print "If no argument is present simulation is built run and script waits for it to end to retrieve log."
            print "\t ping - tests connectivity with the hosts"
            print "\t build - builds jar files for servers and clients"
            print "\t run - runs the simulation remotely"
            print "\t clean - removes all files created on the remote machines"
            print "\t finished - checks if simulation has finished"
            print "\t kill - kills all processes of an ongoing simulation in all machines"
            print "\t log - retrieves the log from the machine that created it. [-n] specifies the log name created on this machine."
            print "\t help - displays this help menu"
            sys.exit()
        else:
            print "Usage: ./SD_script.py ping | build | run | clean | finished | kill | log -n log_name | help"
            print "\t ping - tests connectivity with the hosts"
            print "\t build - builds jar files for servers and clients"
            print "\t run - runs the simulation remotely"
            print "\t clean - removes all files created on the remote machines"
            print "\t finished - checks if simulation has finished"
            print "\t kill - kills all processes of an ongoing simulation in all machines"
            print "\t log - retrieves the log from the machine that created it. [-n] specifies the log name created on this machine."
            print "\t help - displays this help menu"
            sys.exit()
    else:
        main()

#############################################################################################
#
# CREATE JAR FILE FOR SPECIFIC ENTITIES
#    jar cvfe ConfigServer.jar gameoftherope.EntitiesServers.ConfigServer *
#       (example for config server)
#
# CREATE A SCRIPT TO GENERATE FOR ALL ENTITIES
#
#############################################################################################

