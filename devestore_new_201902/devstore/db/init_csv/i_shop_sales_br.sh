#!/bin/sh
echo "##### i_shop_sales_br #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_shop_sales_br.sql -d $2
