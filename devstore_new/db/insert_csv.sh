#!/bin/sh
cd ./db/dump/
psql -Udevelop -P1234 -d store_development_dev < csv_dump.sql