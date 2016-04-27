java -jar ConfigServer.jar &
sleep 1
java -jar BenchServer.jar &
java -jar PlaygroundServer.jar &
java -jar GeneralRepositoryServer.jar &
java -jar RefSiteServer.jar &
sleep 1
java -jar Referee.jar &
java -jar Coach.jar A &
java -jar Coach.jar B &
java -jar Player.jar 0 A &
java -jar Player.jar 1 A &
java -jar Player.jar 2 A &
java -jar Player.jar 3 A &
java -jar Player.jar 4 A &
java -jar Player.jar 0 B &
java -jar Player.jar 1 B &
java -jar Player.jar 2 B &
java -jar Player.jar 3 B &
java -jar Player.jar 4 B
