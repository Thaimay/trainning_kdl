\c store_development_dev;

\COPY I_COMPETITION_BR(competition_br_id,competition_br_cd,competition_br_name,competition_corporate_id,registration_time,registrant_cd,update_time,updater_cd,action,past_id) FROM 'db/csv/I_COMPETITION_BR.csv' WITH CSV;
