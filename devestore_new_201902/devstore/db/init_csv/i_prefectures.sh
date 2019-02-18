#!/bin/sh
echo "##### i_prefectures #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_prefectures.sql -d $2
