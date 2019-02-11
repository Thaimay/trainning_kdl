-- SELECT setval('project_classification_id_seq', (SELECT MAX(id) FROM project_classification));
-- SELECT setval('project_category_classification_id_seq', (SELECT MAX(id) FROM project_category_classification));
-- SELECT setval('project_classificatoin_child_parent_id_seq', (SELECT MAX(id) FROM project_classificatoin_child_parent));

-- INSERT INTO project_classification(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
-- VALUES
-- ('001', '部内検討前', 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '部内検討中', 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '物件検討会承認', 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '経営会議・投資委員会承認', 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '本決裁済み', 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '交渉前', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '交渉中', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '合意済み(詳細交渉中)', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '実施日確定', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL', NOW(), NOW(), 'batch', 'batch', false);

-- INSERT INTO project_classification(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
-- VALUES
-- ('001', '1,14,27,40,61', 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '2,15,28,41,62,95', 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '3,16,29,42,63', 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '4,17,30,43,96', 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '5,18,31,44,64,75,86,97,108', 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '6,19,32,45,53,65,76,87,98', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '7,20,33,46,54,66,77,88,99', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '8,21,34,47,55,67,78,89,100', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false),
-- ('001', '9,22,35,48,56,68,79,90,101', 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM', NOW(), NOW(), 'batch', 'batch', false);

INSERT INTO project_classificatoin_child_parent(corporation_group, parent_id, child_id, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES
('001', (SELECT id FROM project_classification WHERE name = '部内検討前' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '1,14,27,40,61' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '部内検討中' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '2,15,28,41,62,95' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '物件検討会承認' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '3,16,29,42,63' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '経営会議・投資委員会承認' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '4,17,30,43,96' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '本決裁済み' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '5,18,31,44,64,75,86,97,108' AND classification = 'PROJECT_PROGRESS_COMPANY_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '交渉前' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '6,19,32,45,53,65,76,87,98' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '交渉中' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '7,20,33,46,54,66,77,88,99' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '合意済み(詳細交渉中)' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '8,21,34,47,55,67,78,89,100' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false),
('001', (SELECT id FROM project_classification WHERE name = '実施日確定' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL'), (SELECT id FROM project_classification WHERE name = '9,22,35,48,56,68,79,90,101' AND classification = 'PROJECT_PROGRESS_NEGOTIATION_SEARCH_ITEM'), NOW(), NOW(), 'batch', 'batch', false);
