#! /bin/sh

rmiregistry &
rmipid=$!
sleep 1
java implementation.NodeImpl 0 0 0 &
n1=$!
sleep 1
java implementation.NodeImpl 0 0 5 &
n2=$!
sleep 1
java implementation.NodeImpl 0 0 10 &
n3=$!
sleep 1
java implementation.Main 0 0 5 &
main=$!
sleep 1

kill $rmipid $n1 $n2 $n3 $main