#/bin/sh
id="root"
pass="root"

cmd="mysql -u$id -p$pass $tbl"

$cmd < ./db/sql/insert_mysql_user.sql
