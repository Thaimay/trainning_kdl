\c store_development_dev;

\COPY I_COMPETITION(competition_shop_id,competition_shop_cd,competition_shop_name,position_id,competition_br_id,responsible_cd,zone_id,generation_cd,selling_space_address,floor,executing_tsubo,open_date,status,charge_shop_id,registration_time,registrant_cd,update_time,updater_cd,action,past_id) FROM 'db/csv/I_COMPETITION.csv' WITH CSV;
