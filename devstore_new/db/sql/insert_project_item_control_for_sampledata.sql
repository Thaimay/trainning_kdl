-- project

insert into project(
	title,
	project_category_id,
	division,
	open,
	opening_date,
	section_suggest_date,
	building_metting_date,
	consensus_date,
	contract_date,
	office_project_progress_id,
	negotiation_project_progress_id,
	description,
	start_date,
	building_id,
	brand_id,
	in_house_progress,
	negotiation_progress,
	contract_progress,
	m_project_action_status_id,
	investment_discussion_target,
	work_division,
	external_release_start_date,
	external_release_confirm,
	stop,
	stop_reason,
	landing_project_category_id,
	landing_percent,
	landing_memo,
	implementation_datetime,
	i_period_id,
	sales_end_date,
	rent_increase_decrease,
	tenancy,
	sales_expectation,
	profit_rate,
	tenancy_period,
	other_request,
	our_request,
	requestor_name,
	requestor_status,
	operation_form,
	article_review_date,
	article_review_result,
	article_review_result_comment,
	management_date,
	management_result,
	management_result_comment,
	investment_process_date,
	investment_process_result,
	investment_process_result_comment,
	created_datetime,
	update_datetime,
	created_account_code,
	update_account_code,
	is_deleted
) values (
	-- id
	-- corporation_group
	'さんぷる案件', -- title
	1, -- project_category_id
	1, -- division
	false, -- open
	'2018-08-01 00:00:00', -- opening_date
	'2018-08-02 00:00:00', -- section_suggest_date
	'2018-08-03 00:00:00', -- building_metting_date
	'2018-08-04 00:00:00', -- consensus_date
	'2018-08-05 00:00:00', -- contract_date
	1, -- office_project_progress_id
	1, -- negotiation_project_progress_id
	'案件概要情報', -- description
	'2018-08-06 00:00:00', -- start_date
	1, -- building_id
	1, -- brand_id
	'社内進捗', -- in_house_progress
	'交渉進捗', -- negotiation_progress
	'契約進捗', -- contract_progress
	1, -- m_project_action_status_id
	true, -- investment_discussion_target
	'稼動区分', -- work_division
	'2018-08-07 00:00:00', -- external_release_start_date
	'外部公開可能日確定', -- external_release_confirm
	false, -- stop
	'中止理由', -- stop_reason
	1, -- landing_project_category_id
	30.00, -- landing_percent
	'着地補足事項', -- landing_memo
	'2018-08-08 00:00:00', -- implementation_datetime
	1, -- i_period_id
	'2018-08-09 00:00:00', -- sales_end_date
	'賃料増減', -- rent_increase_decrease
	false, -- tenancy
	'1000.00', -- sales_expectation
	'2000.00', -- profit_rate
	'定借満了', -- tenancy_period
	'先方要望', -- other_request
	'当社要望', -- our_request
	'要望先名', -- requestor_name
	'要望先状態', -- requestor_status
	'運営形態', -- operation_form
	'2018-08-10 00:00:00', -- article_review_date
	'物件検討会・結果', -- article_review_result
	'物件検討会・結果コメント', -- article_review_result_comment
	'2018-08-11 00:00:00', -- management_date
	'経営会議・結果', -- management_result
	'経営会議・結果コメント', -- management_result_comment
	'2018-08-12 00:00:00', -- investment_process_date
	'投資上程・結果', -- investment_process_result
	'投資上程・結果コメント', -- investment_process_result_comment
	'2018-08-21 12:07:00', -- created_datetime
	'2018-08-21 12:07:00', -- update_datetime
	'batch', -- created_account_code
	'batch', -- updatedaccount_code
	false -- is_deleted
);

-- project_switing_item_control

insert into project_switing_item_control( 
	category,
	title,
	open_information,
	description,
	start_datetime,
	building_id,
	building_area,
	building_block,
	building_shop_name,
	building_brand,
	business_name,
	ground_floor,
	i_sales_agency_target_id,
	participating_store_corporation_id,
	responsible_store_developer,
	responsible_branch_store,
	responsible_participating_store,
	responsible_human_resource_leader,
	in_house_progress,
	negotiation_progress,
	contract_progress,
	external_release,
	external_release_start_date,
	external_release_confirm,
	action_status,
	action_schedule,
	article_review_datetime,
	article_review_result,
	management_datetime,
	management_result,
	investment_process_datetime,
	investment_process_result,
	investment_process_result_comment,
	contract_update,
	economy_condition,
	tenancy,
	again_contract_start_date,
	again_contract_end_date,
	transfer_division,
	transfer_tenancy,
	transfer_request,
	transfer_implementation_year_month,
	transfer_implementation_day,
	transfer_possibility,
	transfer_period,
	opening_store_form,
	opening_store_section,
	opening_store_sales_division,
	opening_store_adopt_difficulty,
	opening_store_corporation_name,
	redecoration_expect_floor,
	redecoration_ground_floor,
	redecoration_frontage,
	redecoration_last_sales_datetime,
	land_possibility,
	important_contact,
	business_hours,
	business_hours_change_start_date,
	branch_store_opinion,
	bu_opinion,
	newest_plan_information,
	current_contact_form,
	current_contact_start_date,
	current_contact_tenancy_period,
	current_contact_tenancy_date,
	current_contact_economy_condition,
	current_contact_down_rent_date,
	current_contact_history_repair,
	sales_balance_of_income,
	sales_area_1_month,
	sales_expected_building,
	sales_area_1_year,
	sales_wpc_infomation,
	sales_purchasing_guests_rate,
	negotiation_project_approvement,
	negotiation_list,
	task_list,
	divition_image_list,
	divition_document_list,
	remarks,
	manpower_schema,
	needfully_manpower_datetime,
	needfully_manpower_number,
	needfully_manpower_schedule,
	sales_agency_target,
	investment,
	created_datetime,
	update_datetime,
	created_account_code,
	update_account_code,
	is_deleted
) values (
        -- id
        -- corporation_group
        '案件種別', -- category
        false, -- title
        false, -- open_information
        false, -- description
        false, -- start_datetime
        false, -- building_id
        false, -- building_area
        false, -- building_block
        false, -- building_shop_name
        false, -- building_brand
        false, -- business_name
        false, -- ground_floor
        false, -- i_sales_agency_target_id
        false, -- participating_store_corporation_id
        false, -- responsible_store_developer
        false, -- responsible_branch_store
        false, -- responsible_participating_store
        false, -- responsible_human_resource_leader
        false, -- in_house_progress
        false, -- negotiation_progress
        false, -- contract_progress
        false, -- external_release
        false, -- external_release_start_date
        false, -- external_release_confirm
        false, -- action_status
        false, -- action_schedule
        false, -- article_review_datetime
        false, -- article_review_result
        false, -- management_datetime
        false, -- management_result
        false, -- investment_process_datetime
        false, -- investment_process_result
        false, -- investment_process_result_comment
        false, -- contract_update
        false, -- economy_condition
        false, -- tenancy
        false, -- again_contract_start_date
        false, -- again_contract_end_date
        false, -- transfer_division
        false, -- transfer_tenancy
        false, -- transfer_request
        false, -- transfer_implementation_year_month
        false, -- transfer_implementation_day
        false, -- transfer_possibility
        false, -- transfer_period
        false, -- opening_store_form
        false, -- opening_store_section
        false, -- opening_store_sales_division
        false, -- opening_store_adopt_difficulty
        false, -- opening_store_corporation_name
        false, -- redecoration_expect_floor
        false, -- redecoration_ground_floor
        false, -- redecoration_frontage
        false, -- redecoration_last_sales_datetime
        false, -- land_possibility
        false, -- important_contact
        false, -- business_hours
        false, -- business_hours_change_start_date
        false, -- branch_store_opinion
        false, -- bu_opinion
        false, -- newest_plan_information
        false, -- current_contact_form
        false, -- current_contact_start_date
        false, -- current_contact_tenancy_period
        false, -- current_contact_tenancy_date
        false, -- current_contact_economy_condition
        false, -- current_contact_down_rent_date
        false, -- current_contact_history_repair
        false, -- sales_balance_of_income
        false, -- sales_area_1_month
        false, -- sales_expected_building
        false, -- sales_area_1_year
        false, -- sales_wpc_infomation
        false, -- sales_purchasing_guests_rate
        false, -- negotiation_project_approvement
        false, -- negotiation_list
        false, -- task_list
        false, -- divition_image_list
        false, -- divition_document_list
        false, -- remarks
        false, -- manpower_schema
        false, -- needfully_manpower_datetime
        false, -- needfully_manpower_number
        false, -- needfully_manpower_schedule
        false, -- sales_agency_target
        false, -- investment
        '2018-08-22 00:00:00', -- created_datetime
        '2018-08-22 00:00:00', -- update_datetime
        'batch', -- created_account_code
        'batch', -- update_account_code
        false -- is_deleted
);
