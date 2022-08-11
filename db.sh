#!/usr/bin/env bash

if [ "$1" == "up" ]; then
  docker-compose --project-name elukkaisto-db up
elif [ "$1" == "down" ]; then
  docker-compose --project-name elukkaisto-db down --remove-orphans
else
  echo "Anna argumenttina joko 'up' tai 'down'"
fi
