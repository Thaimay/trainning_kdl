#!/bin/sh

pg_dump --no-owner --inserts --use-set-session-authorization -U postgres -c store_development_dev > ./dump/csv_dump.sql

