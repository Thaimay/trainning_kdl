#!/bin/sh
echo "----- production ----"
echo "##### clean database #####"

psql -U wsdadmin -h w8wsdd01.chdtx6vplytr.ap-northeast-1.rds.amazonaws.com -d stored_production -f ./db/sql/clean_database.sql
psql -U wsdadmin -h w8wsdd01.chdtx6vplytr.ap-northeast-1.rds.amazonaws.com -d stored_production -f ./db/sql/db.sql
psql -U wsdadmin -h w8wsdd01.chdtx6vplytr.ap-northeast-1.rds.amazonaws.com -d stored_production -f ./db/sql/db_alter.sql
