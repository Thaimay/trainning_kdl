#!/bin/sh
echo "##### i_steps_rate_calculation_division #####"

export PGPASSWORD=$4
psql -U $3 -h $1 -f ./db/sql/csv/i_steps_rate_calculation_division.sql -d $2
