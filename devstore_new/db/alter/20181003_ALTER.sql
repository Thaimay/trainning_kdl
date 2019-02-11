alter table project add column plan_date date;
alter table project drop column work_division;
alter table project drop column i_period_id;

alter table project_history add column plan_date date;
alter table project_history drop column work_division;
alter table project_history drop column i_period_id;
alter table project_history add column plan_period_half varchar(8);