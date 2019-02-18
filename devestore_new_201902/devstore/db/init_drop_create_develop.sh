#!/bin/bash
echo "----- init ----"
echo "##### create database #####"

export PGPASSWORD=postgres
psql -U postgres -d postgres < ./db/sql/init_sql.sql
