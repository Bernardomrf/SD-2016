java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0103/classes/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     gameoftherope.Registry.ServerRegisterRemoteObject $1 $2