alter table project_task alter column period drop not null;
alter table project_task alter column period type date;
alter table project_task add column period_time time;