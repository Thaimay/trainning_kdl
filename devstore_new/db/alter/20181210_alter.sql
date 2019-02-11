alter table i_shop_sales_br alter column shop_name type varchar(80);
alter table i_shop_sales_trend alter column operating_profit_year_to_year type numeric(16,2);

alter table project drop column operation_form;
alter table project drop column management_form;
alter table project_history drop column operation_form;
alter table project_history drop column management_form;