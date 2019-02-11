CREATE TABLE PROJECT {
  `id` bigint NOT NULL AUTO_INCREMENT UNSIGNED

}

CREATE TABLE NEGOTIATION_INTAVIEW_COMPANY {
  `id`               bigint   NOT NULL AUTO_INCREMENT UNSIGNED,
  `negotiation_id`   bigint   NOT NULL,
  `company_id`       bigint   NOT NULL,
  `created_datetime` datetime NOT NULL,
  `update_datetime`  datetime NOT NULL
}

CREATE TABLE NEGOTIATION {
  `id`                               bigint   NOT NULL AUTO_INCREMENT UNSIGNED,
  `negotiation_divition_id`          bigint   NOT NULL,
  `schedule_datetime`                datetime NOT NULL,
  `implementation_datetime`          datetime NOT NULL,
  `place`                            varchar  NOT NULL,
  `title`                            varchar  NOT NULL,
  `content`                          text     NOT NULL,
  `priority`                         varchar  NOT NULL,
  `next_action_cotent`               text     NOT NULL,
  `next_action_schedule_datetime`    datetime NOT NULL,
  `next_action_schedule_destination` datetime NOT NULL,
  `created_datetime`                 datetime NOT NULL,
  `update_datetime`                  datetime NOT NULL
  FOREIGN KEY (negotiation_divition_id) REFERENCES negotiation_divition (id)
}
