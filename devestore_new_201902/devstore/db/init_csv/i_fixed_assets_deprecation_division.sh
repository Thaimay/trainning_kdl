#!/bin/sh
echo "##### i_fixed_assets_deprecation_division #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_fixed_assets_deprecation_division.sql -d $2
