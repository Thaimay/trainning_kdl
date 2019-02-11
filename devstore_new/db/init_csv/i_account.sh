#!/bin/sh
echo "##### i_account #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_account.sql -d $2
