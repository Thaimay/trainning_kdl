-- PROJECT_SECTION_PROGRESSに案件中(交渉中)の館期待値を移行
alter table project_section_progress add column building_expected_value bigint;
update project_section_progress
  set building_expected_value = (
    select building_expected_value from project
      where project_section_progress.project_id = project.id
  )
  where category = 'NEGOTIATION';
alter table project drop column building_expected_value;

-- PROJECT_SECTION_PROGRESSの案件中(交渉中)の館期待値履歴の移行
alter table project_section_progress_history add column building_expected_value bigint;
update project_section_progress_history
  set building_expected_value = (
    select building_expected_value from project_history
      where project_section_progress_history.project_id = project_history.id
  )
  where category = 'NEGOTIATION';
alter table project_history drop column building_expected_value;

-- SHOPからPROJECT_SECTION_PROGRESSのcategory='CURRENT'のレコードにbuilding_expected_valueを移行させる
update project_section_progress set building_expected_value = (
  select shop.building_expected_value from shop
    inner join i_shop on i_shop.id = shop.i_shop_id
    inner join project on project.i_shop_id = i_shop.id
    where project_section_progress.project_id = project.id
  )
  where project_section_progress.category = 'CURRENT';
