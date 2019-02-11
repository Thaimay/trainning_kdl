echo "----- development ----"
echo "##### alter #####"

export PGPASSWORD=postgres
psql -U postgres -h localhost -d store_development_dev < ./db/step15/alter_1to15_drop.sql
psql -U postgres -h localhost -d store_development_dev < ./db/step15/alter_1to15_create.sql
psql -U postgres -h localhost -d store_development_dev < ./db/step15/alter_1to15_alter.sql
psql -U postgres -h localhost -d store_development_dev < ./db/step15/alter_1to15_insert.sql
psql -U postgres -h localhost -d store_development_dev < ./db/alter/employee_test_insert.sql
