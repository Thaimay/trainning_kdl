#!/bin/sh
echo "##### i_corporation #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_corporation.sql -d $2
