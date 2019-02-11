#!/bin/sh
echo "##### i_area #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_area.sql -d $2
