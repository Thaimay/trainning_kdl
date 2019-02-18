alter table project drop column plan_period;
alter table project add column plan_period int;
alter table project add column plan_period_half varchar(8);