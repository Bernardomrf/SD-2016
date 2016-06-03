#!/bin/bash

ssh sd0103@l040101-ws01.ua.pt "cd Public/classes/ && sh set_rmiregistry_alt.sh 22130" &

echo "Started server..."

sleep 1

ssh sd0103@l040101-ws01.ua.pt "cd Public/classes/ && sh registry.sh" &

sleep 1

echo "Started registry..."

ssh sd0103@l040101-ws02.ua.pt "cd Public/classes/ && sh configRepo.sh" &

sleep 1

echo "Started configRepo..."

ssh sd0103@l040101-ws03.ua.pt "cd Public/classes/ && sh generalRepo.sh" &

sleep 1

echo "Started generalRepo..."

ssh sd0103@l040101-ws04.ua.pt "cd Public/classes/ && sh playground.sh l040101-ws01.ua.pt 22130" &

sleep 1

echo "Started playground..."

ssh sd0103@l040101-ws05.ua.pt "cd Public/classes/ && sh refSite.sh" &

sleep 1

echo "Started refSite..."

ssh sd0103@l040101-ws06.ua.pt "cd Public/classes/ && sh bench.sh" &

sleep 1

echo "Started bench..."

ssh sd0103@l040101-ws07.ua.pt "cd Public/classes/ && sh referee.sh l040101-ws01.ua.pt 22130" &

echo "Started ref..."

sleep 1

ssh sd0103@l040101-ws08.ua.pt "cd Public/classes/ && sh coach.sh l040101-ws01.ua.pt 22130 A" &
sleep 0.1
ssh sd0103@l040101-ws08.ua.pt "cd Public/classes/ && sh coach.sh l040101-ws01.ua.pt 22130 B" &

echo "Started coaches..."

sleep 1

ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 A 0" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 A 1" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 A 2" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 A 3" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 A 4" &
sleep 0.1

ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 B 0" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 B 1" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 B 2" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 B 3" &
sleep 0.1
ssh sd0103@l040101-ws09.ua.pt "cd Public/classes/ && sh player.sh l040101-ws01.ua.pt 22130 B 4"
sleep 0.1

echo "Started players..."

echo "Finished executing..."


