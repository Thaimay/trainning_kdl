#!/bin/sh
echo "##### i_dept #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_dept.sql -d $2
