#!/bin/sh

id="openAM_user"
pass="R-8wAknZr|8D"
table="ninsyo_account"

cmd="mysql -u$id -p$pass $table"

$cmd < ./db/sql/insert_mysql.sql
