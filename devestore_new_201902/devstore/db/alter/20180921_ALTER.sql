alter table project_task alter column important type varchar(128);
update project_task set important = '低';