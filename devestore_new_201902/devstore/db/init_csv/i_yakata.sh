#!/bin/sh
echo "##### i_yakata #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_yakata.sql -d $2
