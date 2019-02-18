#!/bin/sh
echo "##### i_rent_condition #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_rent_condition.sql -d $2
