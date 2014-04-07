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

kill $rmipid $n1 $n2