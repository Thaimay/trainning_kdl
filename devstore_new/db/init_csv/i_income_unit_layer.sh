#!/bin/sh
echo "##### i_income_unit_layer #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_income_unit_layer.sql -d $2
