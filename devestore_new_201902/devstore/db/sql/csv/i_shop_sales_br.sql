\c store_development_dev;
\COPY I_SHOP_SALES_BR(main_income_unit_cd,main_income_unit_name,shop_id,shop_name,open_date,close_date,remodeling_date,tsubo_num,fllor,sales_achievement,sales_achievement_composition,sales_achievement_last_year_difference_composition,sales_achievement_year_to_year,sales_achievement_last_year_difference,operating_profit_achievement,operating_profit_achievement_composition,operating_profit_last_year_difference_composition,operating_profit_year_to_year,operating_profit_last_year_difference,month_area_sales_achievement,month_area_sales_year_to_year,month_area_sales_last_year_difference,disp_order)FROM 'db/csv/I_SHOP_SALES_BR.csv' WITH CSV;
