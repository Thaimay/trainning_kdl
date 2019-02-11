#!/bin/sh
echo "##### i_competition_sales #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_competition_sales.sql -d $2
