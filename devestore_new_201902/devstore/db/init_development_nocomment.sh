#!/bin/sh
echo "----- development ----"
echo "##### create database #####"

export PGPASSWORD=1234
psql -U develop -h localhost -d postgres -f ./db/sql/db.sql
psql -U develop -h localhost -d postgres -f  ./db/sql/db_alter.sql
