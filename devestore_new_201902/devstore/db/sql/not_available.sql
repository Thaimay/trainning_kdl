-- 項目未確定の項目

\c store_development_dev;

CREATE TABLE IF NOT EXISTS ACCOUNT_SUPPORT(
	"id"                              serial primary key,
	"account_id"                      bigint      NOT NULL,
	"created_datetime"                timestamp   NOT NULL,
	"update_datetime"                 timestamp   NOT NULL,
	"created_account_code"            varchar(64) NOT NULL,
	"update_account_code"             varchar(64) NOT NULL,
	"is_deleted"                      boolean     NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS MANAGEMENT_OF_AUTHORITY_ACCOUNT_SETTING(
	"id"                              serial primary key,
	"account_id"                      bigint      NOT NULL,
	"created_datetime"                timestamp   NOT NULL,
	"update_datetime"                 timestamp   NOT NULL,
	"created_account_code"            varchar(64) NOT NULL,
	"update_account_code"             varchar(64) NOT NULL,
	"is_deleted"                      boolean     NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS MANAGEMENT_OF_AUTHORITY_ACCOUNT_ROLE(
	"id"                              serial primary key,
	"account_id"                      bigint      NOT NULL,
	"created_datetime"                timestamp   NOT NULL,
	"update_datetime"                 timestamp   NOT NULL,
	"created_account_code"            varchar(64) NOT NULL,
	"update_account_code"             varchar(64) NOT NULL,
	"is_deleted"                      boolean     NOT NULL DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS ALERT(
	"id"                              serial primary key,
  "alert_category"                  varchar(16) NOT NULL,
	"alert_comment"                   varchar(64) NOT NULL,
	"created_datetime"                timestamp   NOT NULL,
	"update_datetime"                 timestamp   NOT NULL,
	"created_account_code"            varchar(64) NOT NULL,
	"update_account_code"             varchar(64) NOT NULL,
	"is_deleted"                      boolean     NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS PARTICIPATING_STORE_CORPORATION(
	"id"                              serial primary key,
	"corporation_division"            varchar(16)  NOT NULL,
  "corporation_code"                varchar(16)  NOT NULL,
	"corporation_name"                varchar(64)  NOT NULL,
  "corporation_name_kana"           varchar(128) NOT NULL,
	"corporation_postal_code"         varchar(16)  NOT NULL,
  "corporation_street_address"      varchar(128) NOT NULL,
	"phone_number"                    varchar(16)  NOT NULL,
  "fax_number"                      varchar(16)  NOT NULL,
  "corporation_url"                 varchar(512) NOT NULL,
	"created_datetime"                timestamp    NOT NULL,
	"update_datetime"                 timestamp    NOT NULL,
	"created_account_code"            varchar(64)  NOT NULL,
	"update_account_code"             varchar(64)  NOT NULL,
	"is_deleted"                      boolean      NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS SALES_AGENCY_CORPORATION(
	"id"                              serial primary key,
	"corporation_division"            varchar(16)  NOT NULL,
  "corporation_code"                varchar(16)  NOT NULL,
	"corporation_name"                varchar(64)  NOT NULL,
  "corporation_name_kana"           varchar(128) NOT NULL,
	"corporation_postal_code"         varchar(16)  NOT NULL,
  "corporation_street_address"      varchar(128) NOT NULL,
	"phone_number"                    varchar(16)  NOT NULL,
  "fax_number"                      varchar(16)  NOT NULL,
  "corporation_url"                 varchar(512) NOT NULL,
	"created_datetime"                timestamp    NOT NULL,
	"update_datetime"                 timestamp    NOT NULL,
	"created_account_code"            varchar(64)  NOT NULL,
	"update_account_code"             varchar(64)  NOT NULL,
	"is_deleted"                      boolean      NOT NULL DEFAULT FALSE
);

-- 既存の店舗マスタから必要項目をコピーするテーブル 不要かもしれないけどとりあえず定義
CREATE TABLE IF NOT EXISTS SHOP_MASTER(
  "id"                                     serial primary key,
  "shop_master_code"                       varchar(128) NOT NULL,
  "name"                                   varchar(128) NOT NULL,
  "short_abbreviation_name"                varchar(64)  NOT NULL,
  "corporation_code"                       varchar(64)  NOT NULL,
  "building_code"                          varchar(64)  NOT NULL,
  "section"                                varchar(64)  NOT NULL,
  "basis_number"                           int          NOT NULL,
  "frontage"                               int          NOT NULL,
  "sales_substitution_destination_code"    varchar(64)  NOT NULL,
  "sales_substitution_destination_name"    varchar(64)  NOT NULL,
  "participating_store_code"               varchar(64)  NOT NULL,
  "participating_store_name"               varchar(64)  NOT NULL,
  "area"                                   varchar(64)  NOT NULL,
  "brand_code"                             varchar(64)  NOT NULL,
  "brand_name"                             varchar(64)  NOT NULL,
  "open_date"                              timestamp    NOT NULL,
  "close_date"                             timestamp    NOT NULL,
  "building_expected_value"                int          NOT NULL,
  "remarks"                                text         NOT NULL,
  "current_information_plan"               varchar(64)  NOT NULL,
  "type_of_contract"                       varchar(64)  NOT NULL,
  "contract_period"                        varchar(64)  NOT NULL,
  "contract_start_date"                    varchar(64)  NOT NULL,
  "fixed_term"                             varchar(64)  NOT NULL,
  "fixed_expiration_date"                  varchar(64)  NOT NULL,
  "created_datetime"                       timestamp    NOT NULL,
  "update_datetime"                        timestamp    NOT NULL,
  "created_account_code"                   varchar(64)  NOT NULL,
  "update_account_code"                    varchar(64)  NOT NULL,
  "is_deleted"                             boolean      NOT NULL DEFAULT FALSE
);

ALTER TABLE ACCOUNT_SUPPORT OWNER TO develop;
ALTER TABLE MANAGEMENT_OF_AUTHORITY_ACCOUNT_SETTING OWNER TO develop;
ALTER TABLE MANAGEMENT_OF_AUTHORITY_ACCOUNT_ROLE OWNER TO develop;
ALTER TABLE ALERT OWNER TO develop;
ALTER TABLE PARTICIPATING_STORE_CORPORATION OWNER TO develop;
ALTER TABLE SALES_AGENCY_CORPORATION OWNER TO develop;
