\c store_development_dev;
DELETE FROM building_keyman;
DELETE FROM building_personal_develop;
DELETE FROM building_meeting_history;
DELETE FROM pm_corporation;
DELETE FROM building_sales;
DELETE FROM building_trade_area;
DELETE FROM building_tenant;
DELETE FROM activation;
DELETE FROM negotiation_interview_building;
DELETE FROM important_information_building;
DELETE FROM building;
DELETE FROM i_corporation_group;
DELETE FROM i_corporation;
DELETE FROM i_prefectures;
DELETE FROM i_place;
DELETE FROM i_area;
DELETE FROM i_block;
DELETE FROM i_income_unit;
DELETE FROM m_credit_rank;

ALTER SEQUENCE building_keyman_id_seq RESTART 1;
ALTER SEQUENCE building_personal_develop_id_seq RESTART 1;
ALTER SEQUENCE building_meeting_history_id_seq RESTART 1;
ALTER SEQUENCE pm_corporation_id_seq RESTART 1;
ALTER SEQUENCE building_sales_id_seq RESTART 1;
ALTER SEQUENCE building_trade_area_id_seq RESTART 1;
ALTER SEQUENCE building_tenant_id_seq RESTART 1;
ALTER SEQUENCE activation_id_seq RESTART 1;
ALTER SEQUENCE negotiation_interview_building_id_seq RESTART 1;
ALTER SEQUENCE important_information_building_id_seq RESTART 1;
ALTER SEQUENCE building_id_seq RESTART 1;
ALTER SEQUENCE i_corporation_group_id_seq RESTART 1;
ALTER SEQUENCE i_corporation_id_seq RESTART 1;
ALTER SEQUENCE i_prefectures_id_seq RESTART 1;
ALTER SEQUENCE i_place_id_seq RESTART 1;
ALTER SEQUENCE i_area_id_seq RESTART 1;
ALTER SEQUENCE i_block_id_seq RESTART 1;
ALTER SEQUENCE i_income_unit_id_seq RESTART 1;
ALTER SEQUENCE i_sales_agency_target_id_seq RESTART 1;
ALTER SEQUENCE m_credit_rank_id_seq RESTART 1;

INSERT INTO m_credit_rank(id, corporation_group, corporation_cd, credit_rank, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES
(1, '001', '5892', '信用ランク１', '2018-04-03 13:07:39', '2018-04-03 13:07:39', '000000', '000000', FALSE),
(2, '001', '5892', '信用ランク２', '2018-04-03 13:07:39', '2018-04-03 13:07:39', '000000', '000000', FALSE),
(99, '001', '5892', '信用ランク未設定', '2018-04-03 13:07:39', '2018-04-03 13:07:39', '000000', '000000', FALSE);
