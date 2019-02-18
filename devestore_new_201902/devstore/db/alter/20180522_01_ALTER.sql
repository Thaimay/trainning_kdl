CREATE TABLE IF NOT EXISTS NEGOTIATION_INTERVIEW_BUILDING_HISTORY(
  "id"                              serial primary key,
  "corporation_group"                     varchar(4) NOT NULL DEFAULT '001',
  "negotiation_id"                  bigint      NOT NULL,
  "building_id"                     bigint      NOT NULL,
  "created_datetime"                timestamp   NOT NULL,
  "update_datetime"                 timestamp   NOT NULL,
  "created_account_code"            varchar(64) NOT NULL,
  "update_account_code"             varchar(64) NOT NULL,
  "is_deleted"                      boolean     NOT NULL DEFAULT FALSE
);
