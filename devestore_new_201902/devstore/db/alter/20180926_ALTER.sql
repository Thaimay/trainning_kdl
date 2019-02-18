update project set external_release_confirm = 'false';
alter table public.project alter column external_release_confirm type bool using external_release_confirm::bool;

alter table m_period add column corporation_group varchar(4) not null default '001';

alter table project alter column division drop not null;
alter table project add column adopt_difficulty varchar(64);

DROP TABLE IF EXISTS PROJECT_PLAN;
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