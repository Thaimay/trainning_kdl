SELECT setval('project_classification_id_seq', (SELECT MAX(id) FROM project_classification));

INSERT INTO project_classification ("name", "classification", "created_datetime", "update_datetime", "created_account_code", "update_account_code", "is_deleted")
VALUES
('館改装', 'PROJECT_REQUESTOR_REASON', NOW(), NOW(), 'batch', 'batch', false),
('フロア改装', 'PROJECT_REQUESTOR_REASON', NOW(), NOW(), 'batch', 'batch', false),
('その他', 'PROJECT_REQUESTOR_REASON', NOW(), NOW(), 'batch', 'batch', false);


update shop set i_shop_id = i_shop.id from i_shop where shop.i_shop_id = i_shop.shop_id;
