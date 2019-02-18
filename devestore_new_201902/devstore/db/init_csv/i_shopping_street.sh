#!/bin/sh
echo "##### i_shopping_street #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_shopping_street.sql -d $2
