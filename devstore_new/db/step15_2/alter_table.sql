alter table shop add column business_hours varchar(256);
alter table project add is_changed_implementation_schedule_datetime boolean;
alter table project_history add is_changed_implementation_schedule_datetime boolean;
alter table project_plan add column number_of_year float;
alter table file alter column comment drop not null;
alter table i_shop_sales_br alter column shop_name type varchar(80);
alter table i_shop_sales_trend alter column operating_profit_year_to_year type numeric(16,2);
alter table project drop column operation_form;
alter table project drop column management_form;
alter table project_history drop column operation_form;
alter table project_history drop column management_form;
