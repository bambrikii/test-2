#!/bin/bash


COUNTER=3
while [ "$COUNTER" -ge "0" ]; do
#  ls -la
#read -p "input something"
  date
  COUNTER=$((COUNTER - 1))
  sleep 1
done
