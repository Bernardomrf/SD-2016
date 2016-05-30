java -Djava.rmi.server.codebase="file:///home/sd0103/gameoftherope/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     gameoftherope.ClientSide.Referee.RefereeStarter $1 $2
