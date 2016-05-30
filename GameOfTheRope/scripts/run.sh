#!/bin/bash

./set_rmiregistry_alt.sh 22130 &

echo "Started server..."

sleep 1

cd dir_registry
sh registry_com_alt.sh localhost 22130 &
cd ..
sleep 1

echo "Started registry..."


cd dir_configRepo
sh registry_com_alt.sh localhost 22130 &
cd ..
sleep 1

echo "Started configRepo..."


cd dir_generalRepo
sh registry_com_alt.sh localhost 22130 &
cd ..
sleep 1

echo "Started generalRepo..."


cd dir_playground
sh registry_com_alt.sh localhost 22130 &
cd ..
sleep 1

echo "Started playground..."

cd dir_refSite
sh registry_com_alt.sh localhost 22130 &
cd ..
sleep 1

echo "Started refSite..."

cd dir_bench
sh registry_com_alt.sh localhost 22130 &
cd ..
sleep 1

echo "Started bench..."

cd dir_ref
sh registry_com_alt.sh localhost 22130 &
cd ..

echo "Started ref..."

sleep 1

cd dir_coach
sh registry_com_alt.sh localhost 22130 A &
sh registry_com_alt.sh localhost 22130 B &
cd ..

echo "Started coaches..."

sleep 1

cd dir_player
sh registry_com_alt.sh localhost 22130 A 0 &
sh registry_com_alt.sh localhost 22130 A 1 &
sh registry_com_alt.sh localhost 22130 A 2 &
sh registry_com_alt.sh localhost 22130 A 3 &
sh registry_com_alt.sh localhost 22130 A 4 &

sh registry_com_alt.sh localhost 22130 B 0 &
sh registry_com_alt.sh localhost 22130 B 1 &
sh registry_com_alt.sh localhost 22130 B 2 &
sh registry_com_alt.sh localhost 22130 B 3 &
sh registry_com_alt.sh localhost 22130 B 4
cd ..

echo "Started players..."


