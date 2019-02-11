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