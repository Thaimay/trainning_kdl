alter table sub_account add column use_phone boolean default false;
update sub_account set use_phone = true;
