#!/bin/sh
echo "----- development ----"

user=postgres
pass=postgres
db=store_development_dev
host=localhost

export PGPASSWORD=$pass
psql -U $user -h $host -f ./db/sql/csv/delete_csv.sql -d $db

for entry in ./db/init_csv/*;
do
    sh "$entry" $host $db $user $pass
done
sh ./db/building/building.sh $host $db $user $pass

psql -U $user -h $host -f ./db/sql/csv/update_csv.sql -d $db
