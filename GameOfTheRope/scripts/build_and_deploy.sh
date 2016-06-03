mkdir -p deploy/gameoftherope
cp -r ../src/gameoftherope/ deploy/gameoftherope/

cd deploy/
javac gameoftherope/Registry/*.java
javac gameoftherope/ServerSide/Bench/*.java
javac gameoftherope/ServerSide/ConfigRepository/*.java
javac gameoftherope/ServerSide/GeneralRepository/*.java
javac gameoftherope/ServerSide/Playground/*.java
javac gameoftherope/ServerSide/RefSite/*.java
javac gameoftherope/ClientSide/Player/*.java
javac gameoftherope/ClientSide/Coach/*.java
javac gameoftherope/ClientSide/Referee/*.java
javac gameoftherope/VectorClock/*.java

scp -r ./ sd0103@l040101-ws01.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws02.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws03.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws04.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws05.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws06.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws07.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws08.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws09.ua.pt:~/Public/classes/
scp -r ./ sd0103@l040101-ws10.ua.pt:~/Public/classes/


ssh sd0103@l040101-ws01.ua.pt "mkdir -p /home/sd0103/Public/classes"
ssh sd0103@l040101-ws01.ua.pt "mkdir -p /home/sd0103/Public/classes/interfaces"

ssh sd0103@l040101-ws01.ua.pt "cp -r /home/sd0103/deploy/gameoftherope /home/sd0103/Public/classes/"

