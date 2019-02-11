#!/bin/sh
echo "##### i_competition_shop_corporation #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_competition_shop_corporation.sql -d $2
