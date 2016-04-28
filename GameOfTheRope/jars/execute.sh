java -jar ConfigServer.jar &
sleep 1
java -jar BenchServer.jar localhost 22134 &
java -jar PlaygroundServer.jar localhost 22134 &
java -jar GeneralRepositoryServer.jar localhost 22134 &
java -jar RefSiteServer.jar localhost 22134 &
sleep 1
java -jar Referee.jar localhost 22134 &
java -jar Coach.jar A localhost 22134 &
java -jar Coach.jar B localhost 22134 &
java -jar Player.jar 0 A localhost 22134 &
java -jar Player.jar 1 A localhost 22134 &
java -jar Player.jar 2 A localhost 22134 &
java -jar Player.jar 3 A localhost 22134 &
java -jar Player.jar 4 A localhost 22134 &
java -jar Player.jar 0 B localhost 22134 &
java -jar Player.jar 1 B localhost 22134 &
java -jar Player.jar 2 B localhost 22134 &
java -jar Player.jar 3 B localhost 22134 &
java -jar Player.jar 4 B localhost 22134
