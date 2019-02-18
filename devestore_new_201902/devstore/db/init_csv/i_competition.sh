#!/bin/sh
echo "##### i_competition #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_competition.sql -d $2
