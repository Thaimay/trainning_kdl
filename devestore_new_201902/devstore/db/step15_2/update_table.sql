begin;

update project_switing_item_control set basic_profit_expectation = true where id = 1;
update project_switing_item_control set basic_profit_expectation = false where id = 2;
update project_switing_item_control set basic_profit_expectation = false where id = 3;
update project_switing_item_control set basic_profit_expectation = false where id = 4;
update project_switing_item_control set basic_profit_expectation = false where id = 5;
update project_switing_item_control set basic_profit_expectation = false where id = 6;
update project_switing_item_control set basic_profit_expectation = true where id = 7;
update project_switing_item_control set basic_profit_expectation = true where id = 8;
update project_switing_item_control set basic_profit_expectation = true where id = 9;
update project_switing_item_control set basic_profit_expectation = true where id = 10;
update project_switing_item_control set basic_profit_expectation = true where id = 11;

update project_switing_item_control set basic_directionality_landing_directionality_name = true where project_category_id = 1;
update project_switing_item_control set basic_current_contract_rent_reduce_year = false where id = 1;
update project_switing_item_control set basic_current_contract_rent_reduce_year = true where id = 2;
update project_switing_item_control set basic_current_contract_rent_reduce_year = true where id = 3;
update project_switing_item_control set basic_current_contract_rent_reduce_year = true where id = 4;
update project_switing_item_control set basic_current_contract_rent_reduce_year = true where id = 5;
update project_switing_item_control set basic_current_contract_rent_reduce_year = true where id = 6;
update project_switing_item_control set basic_current_contract_rent_reduce_year = false where id = 7;
update project_switing_item_control set basic_current_contract_rent_reduce_year = false where id = 8;
update project_switing_item_control set basic_current_contract_rent_reduce_year = false where id = 9;
update project_switing_item_control set basic_current_contract_rent_reduce_year = false where id = 10;
update project_switing_item_control set basic_current_contract_rent_reduce_year = false where id = 11;

update project_category_classification set project_classification_id = 72 where id = 101;
update project_category set name = '契約期中' where id = 3;
update project_classification set name = '契約期中'  where id = 48;
update project_category set is_deleted = true where id = 4; 
update project_category_classification set project_classification_id = 65 where id = 93;
update project_category set sort = 1 where id = 1;
update project_category set sort = 2 where id = 2;
update project_category set sort = 3 where id = 3;
update project_category set sort = 4 where id = 4;
update project_category set sort = 5 where id = 5;
update project_category set sort = 6 where id = 6;
update project_category set sort = 7 where id = 7;
update project_category set sort = 8 where id = 8;
update project_category set sort = 9 where id = 9;
update project_category set sort = 10 where id = 10;
update project_category set sort = 11 where id = 11;

update m_store_develop_team set is_deleted = true where dept_name in ('計画調整_店舗開発部', 'BtoBｿﾘｭｰｼｮﾝ事業推進', '店舗開発部ｽﾀｯﾌ');

update project set operation_division = '進行中' where operation_division = '案件進行中';
update project set operation_division = '終了' where operation_division = '案件終了';

update i_sales_channel set is_deleted = true where id in (10,11,12);

update project_classification set name = '50%未満' where id = 24;
update project_classification set name = '50%～70%' where id = 25;
update project_classification set name = '70%以上' where id = 26;
update project_classification set name = '100%' where id = 27;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=8;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=9;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=10;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=11;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=17;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=18;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=19;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=20;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=26;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=27;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=28;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=29;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=35;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=36;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=37;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=38;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=42;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=43;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=44;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=45;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=58;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=59;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=60;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=63;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=64;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=65;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=66;
UPDATE public.project_category_classification SET disp_order=1 WHERE id=74;
UPDATE public.project_category_classification SET disp_order=2 WHERE id=75;
UPDATE public.project_category_classification SET disp_order=3 WHERE id=76;
UPDATE public.project_category_classification SET disp_order=4 WHERE id=77;

commit;
