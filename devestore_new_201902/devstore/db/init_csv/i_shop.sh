#!/bin/sh
echo "##### i_shop #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_shop.sql -d $2
