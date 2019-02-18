alter table project_switing_item_control add column rent_contract_startdate_progress boolean default true;
alter table project_switing_item_control add column rent_contract_enddate_progress boolean default true;
alter table project_switing_item_control add column rent_contract_number_of_year_progress boolean default true;

update project_switing_item_control set rent_contract_startdate_progress = false where id = 1;
update project_switing_item_control set rent_contract_enddate_progress = false where id = 1;
update project_switing_item_control set rent_contract_number_of_year_progress = false where id = 1;

update project_switing_item_control set rent_contract_startdate_progress = false where id = 3;
update project_switing_item_control set rent_contract_enddate_progress = false where id = 3;
update project_switing_item_control set rent_contract_number_of_year_progress = false where id = 3;

update project_switing_item_control set basic_management_form = false where id = 1;