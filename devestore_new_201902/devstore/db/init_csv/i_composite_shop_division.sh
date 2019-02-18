#!/bin/sh
echo "##### i_composite_shop_division #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_composite_shop_division.sql -d $2
