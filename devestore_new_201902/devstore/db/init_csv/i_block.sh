#!/bin/sh
echo "##### i_block #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_block.sql -d $2
