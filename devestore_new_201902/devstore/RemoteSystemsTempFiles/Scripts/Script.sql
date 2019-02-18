select contract_start_date,contract_end_date,contract_number_of_year,* from project_contract_progress where project_id = 1607;

select contract_start_date,contract_end_date,contract_number_of_year,* from project_contract_progress order by update_datetime desc;



select adopt_difficulty,* from project  order by update_datetime desc;
select adopt_difficulty,building_id,* from project order by update_datetime desc;
select adopt_difficulty,building_id,* from project where id = 270;
select adopt_difficulty,* from building where id = 3353;

select * from i_sales_agency_contract where is_deleted is false and shop_id = 31743 order by update_datetime desc;
select * from i_sales_agency_condition where is_deleted is false and shop_id = 31743 order by update_datetime desc;
select * from i_sales_agency_condition where is_deleted is false order by update_datetime desc;

select * from i_shop where shop_id = 31737;
select * from i_shop where shop_id = 31743;
select * from i_sales_agency_condition;

select * from i_sales_agency;
select * from i_sales_agency_target;
select * from project_plan;


select basic_Current_Related_Corporation_Group,
basic_Current_Related_Corporation_Sales_Agency,
basic_Current_Related_Corporation_Sales_Agency_Start_Date,
basic_Current_Related_Corporation_Sales_Agency_End_Date,
basic_Current_Related_Corporation_Sales_Agency_Year,
basic_Current_Related_Corporation_Period,
basic_Current_Related_Corporation_Affiliate_Shop, from project_switing_item_control;


select 
basic_Negotiation_Contract_Rent_Reduce_Start_Date, *
from 
project_switing_item_control