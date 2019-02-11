\c store_development_dev;
update building set i_block_id = 1 where i_block_id is null;
update building set i_corporation_id = 1 where i_corporation_id is null;
update building set i_area_id = 1 where i_area_id is null;
update building set i_corporation_id = 1 where i_corporation_id NOT IN(select id from i_corporation);
update i_corporation set corporation_gp_id = 1 where corporation_gp_id is null;
update building set is_building_group = false where is_building_group is null;
update building set is_building_group = false where is_building_group is null;
update building set sales_channel_cd_2 = 1 where sales_channel_cd_2 is null;
update building set credit_rank_id = 1 where credit_rank_id is null;

INSERT INTO m_building_sales_types(value)VALUES("実績（A）");
INSERT INTO m_building_sales_types(value)VALUES("計画（P）");
INSERT INTO m_building_sales_types(value)VALUES("推定（E）");

INSERT INTO m_building_sales_classifications(value)VALUES("ALL");
INSERT INTO m_building_sales_classifications(value)VALUES("直営");
INSERT INTO m_building_sales_classifications(value)VALUES("食品(SM)");
INSERT INTO m_building_sales_classifications(value)VALUES("専門店");
INSERT INTO m_building_sales_classifications(value)VALUES("フロア(B3)");
INSERT INTO m_building_sales_classifications(value)VALUES("フロア(10)");
