#!/bin/bash
cd /opt
cd project
cd MultiDBs-FilesAPIs2DBs-WebClient
git pull
cd ..
cd MultiDBs-FilesAPIs2DBs-Server
git pull
mvn install
cd /opt/project/deployed
rm multidbsfilesapis2dbsserverapi-0.1-SNAPSHOT.jar
cd /opt/project
cd MultiDBs-FilesAPIs2DBs-Server
cd MultiDBsFilesAPIs2DBsServerAPI
cd target
cp multidbsfilesapis2dbsserverapi-0.1-SNAPSHOT.jar ../../../deployed/
cd /opt/project/deployed
kill -9 $(lsof -t -i:7654)
cd /opt/project/deployed
java -jar multidbsfilesapis2dbsserverapi-0.1-SNAPSHOT.jar > log.out 2> error.log < /dev/null &

