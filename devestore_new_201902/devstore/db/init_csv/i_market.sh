#!/bin/sh
echo "##### i_market #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_market.sql -d $2
