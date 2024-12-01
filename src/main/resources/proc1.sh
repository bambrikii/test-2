#!/bin/bash


COUNTER=3
while [ "$COUNTER" -ge "0" ]; do
#  ls -la
#read -p "input something"
  date
  COUNTER=$((COUNTER - 1))
  echo "out:    This is a message ${COUNTER}"
  echo "error:  This is an error message ${COUNTER}" >&2
  sleep 1
done
