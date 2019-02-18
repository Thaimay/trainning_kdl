\c store_development_dev;

\COPY I_COMPETITION_SALES(building_cd,building_name,competition_shop_id,competition_shop_name,tsubo_num,year_month,sales_ratio,input_active_days,disp_order) FROM 'db/csv/I_COMPETITION_SALES.csv' WITH CSV;
