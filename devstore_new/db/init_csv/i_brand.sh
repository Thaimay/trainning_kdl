#!/bin/sh
echo "##### i_brand #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_brand.sql -d $2
