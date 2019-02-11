#!/bin/sh
echo "##### i_income_unit #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_income_unit.sql -d $2
