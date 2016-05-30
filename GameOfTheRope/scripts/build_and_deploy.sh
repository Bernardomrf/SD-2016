mkdir gameoftherope
cp -r ../src/gameoftherope/ gameoftherope/

javac gameoftherope/Registry/*.java
javac gameoftherope/ServerSide/Bench/*.java
javac gameoftherope/ServerSide/ConfigRepository/*.java
javac gameoftherope/ServerSide/GeneralRepository/*.java
javac gameoftherope/ServerSide/Playground/*.java
javac gameoftherope/ServerSide/RefSite/*.java
javac gameoftherope/ClientSide/Player/*.java
javac gameoftherope/ClientSide/Coach/*.java
javac gameoftherope/ClientSide/Referee/*.java

cp -r gameoftherope dir_registry/
cp -r gameoftherope dir_bench/
cp -r gameoftherope dir_configRepo/
cp -r gameoftherope dir_generalRepo/
cp -r gameoftherope dir_playground/
cp -r gameoftherope dir_refSite/
cp -r gameoftherope dir_player/
cp -r gameoftherope dir_coach/
cp -r gameoftherope dir_ref/

scp -r ./ sd0103@l040101-ws02.ua.pt:~

ssh sd0103@l040101-ws02.ua.pt "mkdir -p /home/sd0103/Public/classes"
ssh sd0103@l040101-ws02.ua.pt "mkdir -p /home/sd0103/Public/classes/interfaces"
ssh sd0103@l040101-ws02.ua.pt "cp /home/sd0103/gameoftherope/Interfaces/*.class /home/sd0103/Public/classes/interfaces"

