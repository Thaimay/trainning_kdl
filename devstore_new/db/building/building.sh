#!/bin/sh
echo "##### building #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/building.sql -d $2
