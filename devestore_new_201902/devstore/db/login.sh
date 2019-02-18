#!/bin/sh

export PGPASSWORD=postgres
psql -U develop -d store_development_dev
