#/bin/sh

echo "----- development ----"
echo "##### create database #####"
export PGPASSWORD=postgres
psql -U postgres -f ./db/sql/user_develop.sql -h localhost
