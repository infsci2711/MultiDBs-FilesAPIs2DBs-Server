#!/bin/bash
set -e

echo "installing mysql"

echo mysql-server mysql-server/root_password password $MYSQL_PASSWORD | debconf-set-selections
echo mysql-server mysql-server/root_password_again password $MYSQL_PASSWORD | debconf-set-selections

apt-get install -y mysql-server

echo "updating bind-address"

sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf

echo "starting mysql"

service mysql start

echo "running sql script"

mysql -uroot -p$MYSQL_PASSWORD < /home/files2dbs/db_init.sql

echo "creating folder for servers source code"

cd /opt
mkdir -p project
cd project

mkdir upload

echo "cloning two git repos"

git clone https://github.com/infsci2711/MultiDBs-Utils.git
git clone https://github.com/infsci2711/MultiDBs-FilesAPIs2DBs-Server.git

echo "building utils project"

cd /opt/project/MultiDBs-Utils
mvn install

echo "building server project"

cd /opt/project/MultiDBs-FilesAPIs2DBs-Server
mvn clean
mvn install

echo "creating folder for deployed code"

cd /opt/project
mkdir -p deployed

echo "copying jar and config file to deploed folder"

cp /opt/project/MultiDBs-FilesAPIs2DBs-Server/MultiDBsFilesAPIs2DBsServerAPI/target/multidbsfilesapis2dbsserverapi-0.1-SNAPSHOT.jar /opt/project/deployed
cp /opt/project/MultiDBs-FilesAPIs2DBs-Server/MultiDBsFilesAPIs2DBsServerAPI/src/main/resources/config.properties /opt/project/deployed

echo "starting java server"

nohup java -jar  /opt/project/deployed/multidbsfilesapis2dbsserverapi-0.1-SNAPSHOT.jar /opt/project/deployed/config.properties > /opt/project/deployed/log.out 2> /opt/project/deployed/error.log < /dev/null &

exec "$@"