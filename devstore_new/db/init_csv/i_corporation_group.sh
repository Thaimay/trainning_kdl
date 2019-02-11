#!/bin/sh
echo "##### i_corporation_group #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_corporation_group.sql -d $2
