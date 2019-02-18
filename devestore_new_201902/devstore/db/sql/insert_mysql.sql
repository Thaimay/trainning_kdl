USE `ninsyo_account`;

DROP TABLE IF EXISTS `ninsyo_account`;

CREATE TABLE IF NOT EXISTS `ninsyo_account` (
    uid                                int AUTO_INCREMENT,
    corporation_group                  varchar(4) NOT NULL DEFAULT '001',
    email                              varchar(255) NOT NULL,
    pass                               varchar(40) NOT NULL,
    syaincd                            varchar(8) NOT NULL,
    shimei                             varchar(255) UNIQUE  NOT NULL DEFAULT '',
    approval_flg                       boolean      NOT NULL DEFAULT FALSE,
    last_login_date                    datetime,
    del_flg                            boolean      NOT NULL DEFAULT FALSE,
    insert_date                        datetime,
    insert_user                        varchar(100) ,
    update_date                        datetime,
    update_user                        varchar(100),
    PRIMARY KEY(uid)
);show
