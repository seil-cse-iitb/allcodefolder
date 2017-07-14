#!/bin/bash

checkMongo(){

  #checking if mongo precess is running
  mongostatus=$(ps -edaf | grep 'sudo mongo' | grep -v grep)

  #if length of mongostatus is 0 that means mongo process is not running
  if [ ${#mongostatus} -eq  0 ]
  then
      echo "Mongo server not running"
      echo "Trying to start mongo server..."
      sudo mongod &
      mongostatus=$(ps -edaf | grep 'sudo mongo' | grep -v grep)
      if [ ${#mongostatus} -eq  0 ]
      then
        echo "Unable to start Mongo server"
      else
        echo "Mongo server started"
      fi
  else
      echo "Mongo server running"
  fi
}

checkDjango(){
  djangoStatus=$(ps -edaf | grep 'sudo python' | grep -v grep)

  #checking if djangoStatus varialbe contains manage.py runserver. If it does it means that django server is running.
  if [[ $djangoStatus == *"manage.py runserver"* ]]
  then
    echo "django server running";
  else
    echo "django not running"
    echo "Trying to start django"
    sudo sudo python /home/sid/Work/Aravali/django/aravali/manage.py runserver 0.0.0.0:8000 &

    djangoStatus=$(ps -edaf | grep 'sudo python' | grep -v grep)

    if [[ $djangoStatus == *"manage.py runserver"* ]]
    then
      echo "started django"
    else
      echo "Unable to start django"
    fi
  fi
}

while true
do
  checkMongo
  checkDjango
  sleep 60

done
