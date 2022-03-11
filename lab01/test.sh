#!/bin/bash
for ((c=0; c<30; c++))
do
    result=$((time java -jar out/lab01.jar $1) 2>&1)
    echo $result >> $2
done
