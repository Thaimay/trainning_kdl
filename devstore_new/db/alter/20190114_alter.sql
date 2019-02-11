CREATE TABLE IF NOT EXISTS I_SHOP_TYPE(
    "id"                                     serial primary key,
	"corporation_group"                      varchar(4) NOT NULL DEFAULT '001',
    "shop_type_id"                           bigint NOT NULL,
    "shop_type_cd"                           varchar(2),
    "shop_type_name"                         varchar(40) NOT NULL,
    "coordination_created_datetime"          timestamp,
    "coordination_created_account_code"      varchar(8),
    "coordination_update_datetime"           timestamp,
    "coordination_update_account_code"       varchar(8),
    "action"                                 varchar(2),
    "created_datetime"                       timestamp    NOT NULL,
    "update_datetime"                        timestamp    NOT NULL,
    "created_account_code"                   varchar(64)  NOT NULL,
    "update_account_code"                    varchar(64)  NOT NULL,
    "is_deleted"                             boolean      NOT NULL DEFAULT FALSE
);
