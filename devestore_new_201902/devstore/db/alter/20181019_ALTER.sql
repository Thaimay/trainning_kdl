alter table project add column requestor_status varchar(256);
alter table project_history add column requestor_status varchar(256);

INSERT INTO project_classification(corporation_group, name, classification, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES
('001', '閉館', 'PROJECT_REQUESTOR_REASON', NOW(), NOW(), 'batch', 'batch', false),
('001', '低効率', 'PROJECT_REQUESTOR_REASON', NOW(), NOW(), 'batch', 'batch', false);