\c store_development_dev;
\COPY I_AREA(area_id,area_cd,area_name,wholesale_yn,coordination_created_datetime,coordination_created_account_code,coordination_update_datetime,coordination_update_account_code,action) FROM 'db/csv/I_AREA.csv' WITH CSV;
