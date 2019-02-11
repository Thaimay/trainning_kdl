alter table project drop column plan_status;
alter table project add column plan_status_id bigint;

alter table project_history drop column plan_status;
alter table project_history add column plan_status_id bigint;
