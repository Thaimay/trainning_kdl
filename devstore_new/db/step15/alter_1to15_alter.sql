alter table file add column output_number integer;
alter table file add column output_flag varchar;
alter table file add project_id bigint;
alter table file add column file_division varchar(256);

alter table sub_account add column use_phone boolean default false;
ALTER TABLE negotiation ADD column created_company_cd varchar(2);

update sub_account set use_phone = true;
