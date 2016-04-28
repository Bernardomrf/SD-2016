#!/usr/local/bin/python

import os
import sys
import json
from pyjavaproperties import Properties

settings_file_name = "settings.properties"
server_folder = "~/jars/"
# Dont Change
property_file_name = "config.properties"


info = []
hosts = []
hosts_up = []
jar_info = []
servers = []
clients = []

def main():
    build()
    ping()
    run()

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
            print "Created jar file for " + name
        else:
            print "Error creating jar for " + name
    print "Done creating jar files."

def run():
    ping()
    get_jar_info()
    create_folder()

    new_properties()
    if len(hosts_up) > len(servers):
        for i in range(0,len(servers)):
            changePropertie(servers[i][1], hosts_up[i])


    else:
        # Handle less than 6 machines where servers and players have to co-exist
        print "Error less than 6 machines up, unable to run!!"
        sys.exit()

def run_servers():
    pass

def run_clients():
    pass

def create_folder():
    print "Creating remote folders for jar execution..."
    for i in hosts_up:
        status = ssh(i[0], i[1], "mkdir " + server_folder)
        if status != 0:
            print "Error creating folder in " + i[0] + ", already exists."

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

def ssh(server, username, cmd):
    return os.system("ssh " + username + "@" + server + " \"" + cmd + "\" > /dev/null 2>&1")

def scp(server, username, file):
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

if __name__ == '__main__':
    if len(sys.argv) > 2:
        print "Invalid usage! Only one argument should be provided"
        sys.exit()

    if len(sys.argv) == 2:
        if sys.argv[1] == "ping":
            ping()
        elif sys.argv[1] == "build":
            build()
        elif sys.argv[1] == "run":
            run()
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

