alter table project drop column stop_reason;
alter table project add column stop_reason_id bigint;
alter table project drop column conclusion_possibility_percent;
alter table project add column conclusion_possibility_percent_id bigint;

CREATE TABLE IF NOT EXISTS PROJECT_CATEGORY_CLASSIFICATION(
  "id"                     serial primary key,
  "corporation_group"      varchar(4) NOT NULL DEFAULT '001',
  "project_category_id" int NOT NULL,
  "project_classification_id" int NOT NULL,
  "created_datetime"       timestamp   NOT NULL,
  "update_datetime"        timestamp   NOT NULL,
  "created_account_code"   varchar(64)  NOT NULL,
  "update_account_code"    varchar(64)  NOT NULL,
  "is_deleted"             boolean     NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS PROJECT_CLASSIFICATION(
  "id"                     serial primary key,
  "corporation_group"      varchar(4) NOT NULL DEFAULT '001',
  "name"                   varchar(64) NOT NULL,
  "classification"         varchar(64) NOT NULL,
  "created_datetime"       timestamp   NOT NULL,
  "update_datetime"        timestamp   NOT NULL,
  "created_account_code"   varchar(64)  NOT NULL,
  "update_account_code"    varchar(64)  NOT NULL,
  "is_deleted"             boolean     NOT NULL DEFAULT FALSE
);


INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '経済条件合わず', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '低利益', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '低売上予測', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '館内立地・区画', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '採用運営困難リスク', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, 'コンペ負け', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '低利益・赤字', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '運営体制整わず', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '代行条件合わず', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '販売体制整わず', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '法人与信問題', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '他法人へ切り替え', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '低収益・赤字予測', 'SUSPENSION', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '出店', 'LANDING', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '退店', 'LANDING', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '改装(移)', 'LANDING', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '改装(同)', 'LANDING', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '継続', 'LANDING', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '賃料低減(期中)', 'LANDING', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '30％以下', 'LANDING_POSSIBILITY', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '30％～70％', 'LANDING_POSSIBILITY', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '70％以上', 'LANDING_POSSIBILITY', NOW(), NOW(), '', '', false);
INSERT INTO public.project_classification
(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES('001'::character varying, '100％', 'LANDING_POSSIBILITY', NOW(), NOW(), '', '', false);


INSERT INTO public.project_category_classification
(corporation_group, project_category_id, project_classification_id, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
SELECT '001'::character varying, a.id, b.id, NOW(), NOW(), '', '', false
FROM project_category a
CROSS JOIN project_classification b;