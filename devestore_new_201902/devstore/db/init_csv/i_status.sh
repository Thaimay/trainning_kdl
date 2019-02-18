#!/bin/sh
echo "##### i_status #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_status.sql -d $2
