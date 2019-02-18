#!/bin/sh
echo "##### i_shop_sales_trend #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_shop_sales_trend.sql -d $2
