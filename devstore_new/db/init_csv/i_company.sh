#!/bin/sh
echo "##### i_company #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_company.sql -d $2
