#!/bin/sh
echo "##### i_place #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_place.sql -d $2
