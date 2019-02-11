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
CREATE TABLE IF NOT EXISTS I_PERIOD(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "current_term" int,
    "appropriation_date" date,
    "wpc_date" date,
    "competition_date" date,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);


update project set i_period_id = null;

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
ALTER TABLE PROJECT ADD FOREIGN KEY (landing_project_category_id) REFERENCES project_category(id);
ALTER TABLE PROJECT ADD FOREIGN KEY (i_period_id) REFERENCES i_period(id);
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
