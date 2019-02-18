#!/bin/sh
echo "##### i_remodeling_history #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_remodeling_history.sql -d $2
