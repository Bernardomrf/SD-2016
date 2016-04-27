#!/usr/bin/python

import os
import sys
import json

info = []
hosts = []
hosts_up = []
jar_info = []

def main():
    ping()

def ping():
    print "Pinging servers..."
    get_hosts()
    for i in hosts:
        response = os.system("ping -c 1 " + i + " > /dev/null 2>&1")
        if response != 0:
            print i + ', is down!'
        else:
            hosts_up.append(i)
    print "Finished scanning for active servers, " + str(len(hosts_up)) + " active servers."

def ssh(server, username, password, cmd):
    # Create command with os.system for running ssh remotely
    pass

def run():
    pass

def run_servers():
    pass

def run_clients():
    pass

def build():
    get_jar_info()
    for i in jar_info:
        create_jars(i["name"], i["executable_class"])


def create_jars(name, executable):
    os.chdir("../build/classes")
    os.system("jar cvfe ../../jars/" + name + ".jar " + executable + " *")
    os.chdir("../../scripts")


def get_jar_info():
    global jar_info
    file = open("build.json", 'r').read()
    j = json.loads(file)
    jar_info = j["build-targets"]

def get_hosts():
    global hosts
    file = open("hosts.json", 'r').read()
    j = json.loads(file)
    info = j["hosts"]
    hosts = [host["hostname"] for host in info]

if __name__ == '__main__':
    if len(sys.argv) > 2:
        print "Invalid usage! Only one argument should be provided"
        sys.exit()

    if len(sys.argv) == 2:
        if sys.argv[1] == "ping":
            ping()
        elif sys.argv[1] == "build":
            build()
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

