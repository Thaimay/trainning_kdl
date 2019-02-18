\c store_development_dev;
\COPY I_BLOCK(block_id,block_cd,block_name,coordination_created_datetime,coordination_created_account_code,coordination_update_datetime,coordination_update_account_code,action)FROM 'db/csv/I_BLOCK.csv' WITH CSV;
