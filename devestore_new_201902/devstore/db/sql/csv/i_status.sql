\c store_development_dev;
\COPY I_STATUS(status_id,status_cd,status_name,coordination_created_datetime,coordination_created_account_code,coordination_update_datetime,coordination_update_account_code,action) FROM 'db/csv/I_STATUS.csv' WITH CSV;
