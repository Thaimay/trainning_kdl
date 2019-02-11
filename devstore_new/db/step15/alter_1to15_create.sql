CREATE TABLE IF NOT EXISTS project (
id serial primary key
, corporation_group character varying(4) default '001' not null
, title character varying(256) not null
, project_category_id bigint not null
, division character varying(256)
, open boolean
, office_project_progress_id bigint
, negotiation_project_progress_id bigint
, description character varying(256)
, start_date date
, building_id bigint
, i_shop_id bigint
, shop_name character varying(256)
, shop_cd character varying(64)
, corporation_id bigint
, corporation_gp_id bigint
, brand_id bigint
, i_area_id bigint
, i_block_id bigint
, i_prefectures_id bigint
, adopt_difficulty character varying(4)
, current_i_sales_agency_target_id bigint
, progress_i_sales_agency_target_id bigint
, action_status character varying(256)
, in_house_progress character varying(256)
, negotiation_progress character varying(256)
, contract_progress character varying(256)
, m_project_action_status_id bigint
, building_expected_value bigint
, sales_prediction bigint
, investment_discussion_target boolean
, external_release_start_date date
, external_release_confirm character varying(256)
, stop boolean
, stop_reason_id bigint
, landing_project_category_id bigint
, conclusion_possibility_percent_id bigint
, landing_memo text
, implementation_datetime date
, i_sales_channel_id bigint
, management_form character varying(256)
, sales_end_date date
, rent_increase_decrease character varying(256)
, tenancy boolean
, profit_rate double precision
, tenancy_period character varying(256)
, other_request character varying(256)
, our_request character varying(256)
, requestor_name character varying(256)
, operation_form character varying(256)
, article_review_date date
, article_review_result_id bigint
, article_review_result_comment text
, management_date date
, management_result_id bigint
, management_result_comment text
, investment_process_date date
, investment_process_result_id bigint
, investment_process_result_comment text
, operation_division character varying(64)
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
, plan_period integer
, plan_period_half character varying(8)
, plan_date date
, plan_status_id bigint
, implementation_schedule_datetime date
, shop_name_temporary character varying(256)
, profit_expectation double precision
, requestor_status character varying(256)
);
CREATE TABLE IF NOT EXISTS OTHER_PROJECT_ACCOUNT(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint,
    "category" varchar(64),
    "i_account_id" bigint,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_OPINION(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint,
    "file_id" bigint,
    "category" varchar(256),
    "title" varchar(256),
    "comment" text,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_CATEGORY(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "name" varchar(256),
    "sort" int,
    "category" varchar(32),
    "category_name" varchar(32),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_PROGRESS(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "category" varchar(32),
    "priority" int,
    "code" varchar(256),
    "name" varchar(256),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_SECTION_PROGRESS(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint,
    "category" varchar(32),
    "section" varchar(256),
    "frontage" varchar(256),
    "floor" varchar(256),
    "contract_tsubo" numeric,
    "business_hours" varchar(256),
    "memo" text,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_PLAN(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint,
    "category" varchar,
    "sales_agency_target_id" bigint,
    "start_date"date,
    "end_date"date,
    "participating_store_corporation_id" bigint,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS project_contract_progress (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, form character varying(256)
, category character varying(64)
, contract_target_name character varying(256)
, contract_start_date date
, contract_end_date date
, contract_number_of_year double precision
, auto_update boolean
, memo text
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
, rent_start_date date
, rent_end_date date
, rent_year double precision
);
CREATE TABLE IF NOT EXISTS PROJECT_PAST_CONFERENCE(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint,
    "name" varchar(256),
    "schedule_date" date,
    "result" varchar(256),
    "attendees" varchar(256),
    "comment" varchar(256),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_NEGOTIATION(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint NOT NULL,
    "negotiation_id" bigint NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS project_task (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint not null
, period date
, important character varying(128) default '中' not null
, complete boolean
, comment text not null
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
, period_time time without time zone
);
CREATE TABLE IF NOT EXISTS PROJECT_TASK_OWNER(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_task_id" bigint NOT NULL,
    "i_account_id" bigint NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_TASK_ACCOUNT(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_task_id" bigint NOT NULL,
    "account_id" bigint NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_READ_LATER_ACCOUNT(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint NOT NULL,
    "account_id" bigint NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_PERSONAL_DEVELOP(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint NOT NULL,
    "account_id" bigint NOT NULL,
    "category" varchar(64) NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS project_switing_item_control (
id serial primary key
,corporation_group character varying(4) default '001' not null
,project_category_id bigint not null
,basic_building boolean not null
,basic_channel boolean not null
,basic_corporation_group boolean not null
,basic_corporation boolean not null
,basic_shop_name boolean not null
,basic_shop boolean not null
,basic_shop_cd boolean not null
,basic_tsubo boolean not null
,basic_management_form boolean not null
,basic_brand boolean not null
,basic_area boolean not null
,basic_adopt_difficulty boolean not null
,basic_block boolean not null
,basic_prefectures boolean not null
,basic_investment_discussion boolean not null
,basic_work_division boolean not null
,basic_sales_agency boolean not null
,basic_affiliate_shop_corporation boolean not null
,basic_in_house_progress boolean not null
,basic_negotiation_progress boolean not null
,basic_external_release_start_date boolean not null
,basic_external_release_confirm boolean not null
,basic_action_status boolean not null
,basic_project_start_date boolean not null
,basic_stop boolean not null
,basic_stop_reason boolean not null
,basic_description boolean not null
,basic_plan boolean not null
,basic_plan_implementation_date boolean not null
,basic_plan_period boolean not null
,basic_other_request boolean not null
,basic_our_request boolean not null
,basic_directionality_group boolean not null
,basic_directionality_landing_directionality_name boolean not null
,basic_directionality_landing_directionality_percent boolean not null
,basic_directionality_requestor_name boolean not null
,basic_directionality_requestor_status boolean not null
,basic_directionality_landing_directionality_memo boolean not null
,basic_directionality_rent_increase_decrease boolean not null
,basic_directionality_tenancy boolean not null
,basic_directionality_sales_prediction boolean not null
,basic_directionality_tenancy_period boolean not null
,basic_directionality_profit_rate boolean not null
,basic_directionality_implementation_datetime boolean not null
,basic_directionality_redecoration_last_sales_date boolean not null
,basic_directionality_implementation_period boolean not null
,basic_directionality_management_form boolean not null
,basic_current_store_group boolean not null
,basic_current_store_section boolean not null
,basic_current_store_frontage boolean not null
,basic_current_store_floor boolean not null
,basic_current_store_contract_tsubo boolean not null
,basic_current_store_business_hours boolean not null
,basic_current_store_memo boolean not null
,basic_current_store_building_expected_value boolean not null
,basic_negotiation_store_group boolean not null
,basic_negotiation_store_section boolean not null
,basic_negotiation_store_frontage boolean
,basic_negotiation_store_floor boolean not null
,basic_negotiation_store_contract_tsubo boolean not null
,basic_negotiation_store_contract_tsubo_increase_decrease boolean not null
,basic_negotiation_store_business_hours boolean not null
,basic_negotiation_store_memo boolean not null
,basic_negotiation_store_expected_value boolean not null
,basic_current_contract_group boolean not null
,basic_current_contract_form boolean not null
,basic_current_contract_name boolean not null
,basic_current_contract_start_date boolean not null
,basic_current_contract_end_date boolean not null
,basic_current_contract_year boolean not null
,basic_current_contract_tenancy_expiration_period boolean not null
,basic_current_contract_auto_update boolean not null
,basic_current_contract_economic_condition boolean not null
,basic_current_contract_rent_reduce_start_date boolean not null
,basic_current_contract_rent_reduce_end_date boolean not null
,basic_current_contract_memo boolean not null
,basic_negotiation_contract_group boolean not null
,basic_negotiation_contract_form boolean not null
,basic_negotiation_contract_name boolean not null
,basic_negotiation_contract_start_date boolean not null
,basic_negotiation_contract_end_date boolean not null
,basic_negotiation_contract_year boolean not null
,basic_negotiation_contract_tenancy_expiration_period boolean not null
,basic_negotiation_contract_auto_update boolean not null
,basic_negotiation_contract_memo boolean not null
,basic_current_related_corporation_group boolean not null
,basic_current_related_corporation_sales_agency boolean not null
,basic_current_related_corporation_sales_agency_start_date boolean not null
,basic_current_related_corporation_sales_agency_end_date boolean not null
,basic_current_related_corporation_sales_agency_year boolean not null
,basic_current_related_corporation_affiliate_shop boolean not null
,basic_negotiation_related_corporation_group boolean not null
,basic_negotiation_related_corporation_sales_agency boolean not null
,basic_negotiation_related_corporation_sales_agency_start_date boolean not null
,basic_negotiation_related_corporation_sales_agency_end_date boolean not null
,basic_negotiation_related_corporation_sales_agency_year boolean not null
,basic_negotiation_related_corporation_affiliate_shop boolean not null
,basic_branch_store_staff_opinion boolean not null
,basic_branch_store_opinion boolean not null
,basic_bu_opinion boolean not null
,schedule_action_schedule_group boolean not null
,schedule_article_review_datetime boolean not null
,schedule_article_review_result boolean not null
,schedule_article_review_result_comment boolean not null
,schedule_management_datetime boolean not null
,schedule_management_result boolean not null
,schedule_management_result_comment boolean not null
,schedule_investment_process_datetime boolean not null
,schedule_investment_process_result boolean not null
,schedule_investment_process_result_comment boolean not null
,schedule_past_meeting_group boolean not null
,schedule_person_store_development_team boolean not null
,schedule_person_store_development boolean not null
,schedule_person_branch_store_sales boolean not null
,schedule_person_business boolean not null
,schedule_person_franchise boolean not null
,schedule_person_leader boolean
,schedule_person_other boolean not null
,negotiation_tab boolean not null
,task_tab boolean not null
,building_and_store_tab boolean not null
,image_and_file_tab boolean not null
,created_datetime timestamp(6) without time zone not null
,update_datetime timestamp(6) without time zone not null
,created_account_code character varying(64) not null
,update_account_code character varying(64) not null
,is_deleted boolean default false not null
,basic_current_contract_rent_reduce_year boolean
,basic_negotiation_contract_rent_reduce_start_date boolean
,basic_negotiation_contract_rent_reduce_end_date boolean
,basic_negotiation_contract_rent_reduce_year boolean
,basic_current_related_corporation_period boolean
,basic_negotiation_related_corporation_period boolean
,basic_profit_expectation boolean
,basic_directionality_profit_amount boolean
);
CREATE TABLE IF NOT EXISTS FILE_DIVISION(
    "id" serial primary key,
    "corporation_group" varchar(4) DEFAULT '001' NOT NULL,
    "file_division_code" varchar(128) NOT NULL,
    "display_name" varchar(128) NOT NULL,
    "negotiation_flag" boolean NOT NULL,
    "building_flag" boolean NOT NULL,
    "shop_flag" boolean NOT NULL,
    "project_flag" boolean NOT NULL,
    "list_order" int NOT NULL,
    "select_order" int NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_PROJECT_ACTION_STATUS(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_category_id" bigint NOT NULL,
    "sales_channel_id" bigint NOT NULL,
    "name" varchar(64) NOT NULL,
    "sort" int NOT NULL,
    "company_status_code" varchar(32) NOT NULL,
    "negotiation_status_code" varchar(32) NOT NULL,
    "other_status_code" varchar(32) NOT NULL,
    "schedule_use" boolean NOT NULL DEFAULT FALSE,
    "schedule" varchar(4) NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_SCHEDULE(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint NOT NULL,
    "m_project_action_status_id" bigint NOT NULL,
    "schedule_date" date,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_PROJECT_PROGRESS_STATUS(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "category" varchar(32),
    "project_category_id" bigint,
    "priority" int,
    "code" varchar(256),
    "name" varchar(256),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_INCOME_UNIT_AREA(
  "id" serial primary key,
  "corporation_group" varchar(4) NOT NULL DEFAULT '001',
  "income_unit_cd" varchar(18) NOT NULL,
  "area_cd" varchar(6) NOT NULL,
  "created_datetime" timestamp NOT NULL,
  "update_datetime" timestamp NOT NULL,
  "created_account_code" varchar(64) NOT NULL,
  "update_account_code" varchar(64) NOT NULL,
  "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_PROJECT_MEETING_RESULT(
  "id"                     serial primary key,
  "corporation_group"      varchar(4) NOT NULL DEFAULT '001',
  "name"                   varchar(32) NOT NULL,
  "priority"               int NOT NULL,
  "created_datetime"       timestamp   NOT NULL,
  "update_datetime"        timestamp   NOT NULL,
  "created_account_code"   varchar(64)  NOT NULL,
  "update_account_code"    varchar(64)  NOT NULL,
  "is_deleted"             boolean     NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_CATEGORY_CLASSIFICATION(
  "id"                     serial primary key,
  "corporation_group"      varchar(4) NOT NULL DEFAULT '001',
  "project_category_id" int NOT NULL,
  "project_classification_id" int NOT NULL,
  "created_datetime"       timestamp   NOT NULL,
  "update_datetime"        timestamp   NOT NULL,
  "created_account_code"   varchar(64)  NOT NULL,
  "update_account_code"    varchar(64)  NOT NULL,
  "is_deleted"             boolean     NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_CLASSIFICATION(
  "id"                     serial primary key,
  "corporation_group"      varchar(4) NOT NULL DEFAULT '001',
  "name"                   varchar(64) NOT NULL,
  "classification"         varchar(64) NOT NULL,
  "created_datetime"       timestamp   NOT NULL,
  "update_datetime"        timestamp   NOT NULL,
  "created_account_code"   varchar(64)  NOT NULL,
  "update_account_code"    varchar(64)  NOT NULL,
  "is_deleted"             boolean     NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_LOCALITY(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "i_area_id" bigint NOT NULL,
    "i_prefecture_id" bigint NOT NULL,
    "i_block_id" bigint NOT NULL,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS OTHER_PROJECT_TEAM(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "project_id" bigint,
    "category" varchar(64),
    "income_unit_cd" varchar(8),
    "dept_cd" varchar(8),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_PERIOD (
    "id" serial primary key,
    "corporation_group"      varchar(4) NOT NULL DEFAULT '001',
    "period" int UNIQUE NOT NULL,
    "start_date" date,
    "end_date" date,
    "created_datetime"                   timestamp   default CURRENT_TIMESTAMP,
    "update_datetime"                    timestamp   default CURRENT_TIMESTAMP,
    "created_account_code"               varchar(64) default '00000000',
    "update_account_code"                varchar(64) default '00000000',
    "is_deleted"                         boolean     default false
);
CREATE TABLE IF NOT EXISTS M_STORE_DEVELOP_TEAM(
    "id"                                serial PRIMARY KEY,
    "corporation_group"                 varchar(4) NOT NULL DEFAULT '001',
    "income_unit_cd"                    varchar(6)     ,
    "income_unit_name"                  varchar(40)    ,
    "dept_cd"                           varchar(6)     NOT NULL,
    "dept_name"                         varchar(40)   NOT NULL ,
    "created_datetime"                  timestamp   default CURRENT_TIMESTAMP,
    "update_datetime"                   timestamp   default CURRENT_TIMESTAMP,
    "created_account_code"              varchar(64) default '00000000',
    "update_account_code"               varchar(64) default '00000000',
    "is_deleted"                        boolean     default false
);

CREATE TABLE project_classificatoin_child_parent (
 id serial PRIMARY KEY,
 corporation_group varchar(4) NOT NULL DEFAULT '001',
 parent_id int4 NOT NULL,
 child_id int4 NOT NULL,
 created_datetime timestamp NOT NULL,
 update_datetime timestamp NOT NULL,
 created_account_code varchar(64) NOT NULL,
 update_account_code varchar(64) NOT NULL,
 is_deleted bool NOT NULL DEFAULT false
);

create table project_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, title character varying(256) not null
, project_category_id bigint not null
, division character varying(256)
, open boolean
, office_project_progress_id bigint
, negotiation_project_progress_id bigint
, description character varying(256)
, start_date date
, building_id bigint
, i_shop_id bigint
, shop_name character varying(256)
, shop_cd character varying(64)
, corporation_id bigint
, corporation_gp_id bigint
, brand_id bigint
, i_area_id bigint
, i_block_id bigint
, i_prefectures_id bigint
, adopt_difficulty character varying(64)
, current_i_sales_agency_target_id bigint
, progress_i_sales_agency_target_id bigint
, action_status character varying(256)
, in_house_progress character varying(256)
, negotiation_progress character varying(256)
, contract_progress character varying(256)
, m_project_action_status_id bigint
, building_expected_value bigint
, sales_prediction bigint
, investment_discussion_target boolean
, external_release_start_date date
, external_release_confirm character varying(256)
, stop boolean
, stop_reason_id bigint
, landing_project_category_id bigint
, conclusion_possibility_percent_id bigint
, landing_memo text
, implementation_datetime date
, plan_period integer
, plan_period_half character varying(8)
, i_sales_channel_id bigint
, management_form character varying(256)
, sales_end_date date
, rent_increase_decrease character varying(256)
, tenancy boolean
, profit_rate double precision
, tenancy_period character varying(256)
, other_request character varying(256)
, our_request character varying(256)
, requestor_name character varying(256)
, operation_form character varying(256)
, article_review_date date
, article_review_result_id bigint
, article_review_result_comment text
, management_date date
, management_result_id bigint
, management_result_comment text
, investment_process_date date
, investment_process_result_id bigint
, investment_process_result_comment text
, operation_division character varying(64)
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
, plan_date date
, plan_status_id bigint
, implementation_schedule_datetime date
, shop_name_temporary character varying(256)
, profit_expectation double precision
, requestor_status character varying(256)
);

create table project_section_progress_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, category character varying(32)
, section character varying(256)
, frontage character varying(256)
, floor character varying(256)
, contract_tsubo numeric
, business_hours character varying(256)
, memo text
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
);
create table project_plan_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, category character varying
, sales_agency_target_id bigint
, start_date date
, end_date date
, participating_store_corporation_id bigint
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
);
create table project_contract_progress_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, form character varying(256)
, category character varying(64)
, contract_target_name character varying(256)
, contract_start_date date
, contract_end_date date
, contract_number_of_year double precision
, auto_update boolean
, memo text
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
, rent_start_date date
, rent_end_date date
, rent_year double precision
, operation_form character varying(256)
);
create table other_project_account_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, category character varying(64)
, i_account_id bigint
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
);
create table other_project_team_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, category character varying(64)
, income_unit_cd character varying(8)
, dept_cd character varying(8)
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
);
create table project_opinion_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint
, file_id bigint
, category character varying(256)
, title character varying(256)
, comment text
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
);
create table project_schedule_history (
id serial primary key
, corporation_group character varying(4) default '001' not null
, project_id bigint not null
, m_project_action_status_id bigint not null
, schedule_date date
, created_datetime timestamp(6) without time zone not null
, update_datetime timestamp(6) without time zone not null
, created_account_code character varying(64) not null
, update_account_code character varying(64) not null
, is_deleted boolean default false not null
);


ALTER TABLE PROJECT ADD FOREIGN KEY (building_id) REFERENCES building(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (i_shop_id) REFERENCES i_shop(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (corporation_id) REFERENCES i_corporation(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (corporation_gp_id) REFERENCES i_corporation_group(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (brand_id) REFERENCES brand(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (i_area_id) REFERENCES i_area(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (i_block_id) REFERENCES i_block(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (i_prefectures_id) REFERENCES i_prefectures(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (current_i_sales_agency_target_id) REFERENCES i_sales_agency_target(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (progress_i_sales_agency_target_id) REFERENCES i_sales_agency_target(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (landing_project_category_id) REFERENCES project_classification(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (i_sales_channel_id) REFERENCES i_sales_channel(id);

ALTER TABLE OTHER_PROJECT_ACCOUNT ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_OPINION ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_OPINION ADD FOREIGN KEY (file_id) REFERENCES file(id);

ALTER TABLE PROJECT_SECTION_PROGRESS ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_PLAN ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_CONTRACT_PROGRESS ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_PAST_CONFERENCE ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_NEGOTIATION ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_NEGOTIATION ADD FOREIGN KEY (negotiation_id) REFERENCES negotiation(id);
ALTER TABLE PROJECT_TASK ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_TASK_OWNER ADD FOREIGN KEY (project_task_id) REFERENCES project_task(id);
ALTER TABLE PROJECT_TASK_OWNER ADD FOREIGN KEY (i_account_id) REFERENCES i_account(id);

ALTER TABLE PROJECT_TASK_ACCOUNT ADD FOREIGN KEY (project_task_id) REFERENCES project_task(id);
ALTER TABLE PROJECT_TASK_ACCOUNT ADD FOREIGN KEY (account_id) REFERENCES i_account(id);
ALTER TABLE PROJECT_READ_LATER_ACCOUNT ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_READ_LATER_ACCOUNT ADD FOREIGN KEY (account_id) REFERENCES i_account(id);

ALTER TABLE PROJECT_PERSONAL_DEVELOP ADD FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE PROJECT_PERSONAL_DEVELOP ADD FOREIGN KEY (account_id) REFERENCES i_account(id);
