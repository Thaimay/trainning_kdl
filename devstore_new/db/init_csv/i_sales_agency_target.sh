#!/bin/sh
echo "##### i_sales_agency_target #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_sales_agency_target.sql -d $2
