#!/bin/sh
echo "##### i_shop_sales #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_shop_sales.sql -d $2
