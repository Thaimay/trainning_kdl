#!/bin/sh
echo "##### i_md_calendar #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_md_calendar.sql -d $2
