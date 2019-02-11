alter table file add column file_division varchar(256);

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