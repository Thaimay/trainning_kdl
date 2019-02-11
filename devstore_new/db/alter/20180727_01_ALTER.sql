CREATE TABLE IF NOT EXISTS ACCOUNT_DATA(
    "id"                                     serial       primary key,
    "corporation_group"                      varchar(4) NOT NULL DEFAULT '001',
    "account_id"                             bigint       NOT NULL,
    "data_user"                              text NULL,
    "created_datetime"                       timestamp    NOT NULL,
    "update_datetime"                        timestamp    NOT NULL,
    "created_account_code"                   varchar(64)  NOT NULL,
    "update_account_code"                    varchar(64)  NOT NULL,
    "is_deleted"                             boolean      NOT NULL DEFAULT FALSE
);

alter table i_account drop column data_user;