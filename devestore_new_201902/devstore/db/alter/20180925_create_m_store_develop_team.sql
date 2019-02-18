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

BEGIN;
insert into M_STORE_DEVELOP_TEAM (income_unit_cd,income_unit_name,dept_cd,dept_name) values
(null,null,'951430','計画調整_店舗開発部'),
(null,null,'952770','SC店舗開発'),
(null,null,'952780','ﾀｰﾐﾅﾙ店舗開発課'),
(null,null,'952830','BtoBｿﾘｭｰｼｮﾝ事業推進'),
(null,null,'952790','百貨店店舗開発'),
(null,null,'952810','店舗開発部ｽﾀｯﾌ');
COMMIT;
