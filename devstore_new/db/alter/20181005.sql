DROP TABLE project_classificatoin_child_parent;
CREATE TABLE project_classificatoin_child_parent(
  "id"                    serial primary key,
  "corporation_group"     varchar(4) NOT NULL DEFAULT '001',
  "parent_id"             int NOT NULL,
  "child_id"              int NOT NULL,
  "created_datetime"      timestamp    NOT NULL,
  "update_datetime"       timestamp    NOT NULL,
  "created_account_code"  varchar(64)  NOT NULL,
  "update_account_code"   varchar(64)  NOT NULL,
  "is_deleted"            boolean      NOT NULL DEFAULT FALSE
);

SELECT setval('project_classification_id_seq', (SELECT MAX(id) FROM project_classification));
SELECT setval('project_category_classification_id_seq', (SELECT MAX(id) FROM project_category_classification));

INSERT INTO project_classification(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES
('001', '出店', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '定借満了', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '満了以外', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '賃料低減', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '時短交渉', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '販売代行', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '外貨獲得', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '催事出店', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', 'その他', 'PROJECT_CATEGORY_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '出店', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '改装', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '退店', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '移設', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '継続', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '条変', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '契約', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '終了', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', '中止', 'LANDING_SHORT_NAME', NOW(), NOW(), 'batch', 'batch', false),
('001', 'rgb(0, 112, 192)', 'PROJECT_CATEGORY_COLOR', NOW(), NOW(), 'batch', 'batch', false),
('001', 'rgb(255, 51, 153)', 'PROJECT_CATEGORY_COLOR', NOW(), NOW(), 'batch', 'batch', false);

INSERT INTO project_category_classification(corporation_group, project_category_id, project_classification_id, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES
('001', 1, (SELECT id FROM project_classification WHERE name = '出店' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 2, (SELECT id FROM project_classification WHERE name = '定借満了' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 3, (SELECT id FROM project_classification WHERE name = '満了以外' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 4, (SELECT id FROM project_classification WHERE name = '賃料低減' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 5, (SELECT id FROM project_classification WHERE name = '時短交渉' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 6, (SELECT id FROM project_classification WHERE name = '販売代行' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 7, (SELECT id FROM project_classification WHERE name = '販売代行' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 8, (SELECT id FROM project_classification WHERE name = '外貨獲得' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 9, (SELECT id FROM project_classification WHERE name = '催事出店' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 10, (SELECT id FROM project_classification WHERE name = 'その他' AND classification = 'PROJECT_CATEGORY_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', 1, (SELECT id FROM project_classification WHERE name = 'rgb(0, 112, 192)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 2, (SELECT id FROM project_classification WHERE name = 'rgb(0, 112, 192)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 3, (SELECT id FROM project_classification WHERE name = 'rgb(0, 112, 192)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 4, (SELECT id FROM project_classification WHERE name = 'rgb(0, 112, 192)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 5, (SELECT id FROM project_classification WHERE name = 'rgb(0, 112, 192)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 6, (SELECT id FROM project_classification WHERE name = 'rgb(0, 112, 192)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 7, (SELECT id FROM project_classification WHERE name = 'rgb(255, 51, 153)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 8, (SELECT id FROM project_classification WHERE name = 'rgb(255, 51, 153)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 9, (SELECT id FROM project_classification WHERE name = 'rgb(255, 51, 153)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false),
('001', 10, (SELECT id FROM project_classification WHERE name = 'rgb(255, 51, 153)' AND classification = 'PROJECT_CATEGORY_COLOR'), NOW(), NOW(), 'batch', 'batch', false);


INSERT INTO project_classificatoin_child_parent(corporation_group, parent_id, child_id, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES
('001', (SELECT id FROM project_classification WHERE name = '出店' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '出店' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '退店' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '退店' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '改装(移)' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '移設' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '改装(同)' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '改装' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '継続' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '継続' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '条件変更' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '条変' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '契約' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '契約' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '終了' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '終了' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '中止' AND classification = 'LANDING'), (SELECT id FROM project_classification WHERE name = '中止' AND classification = 'LANDING_SHORT_NAME'), NOW(), NOW(), 'batch', 'batch', false);
