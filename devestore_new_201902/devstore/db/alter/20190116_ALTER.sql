CREATE TABLE IF NOT EXISTS M_SHOP_TYPE(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "name" varchar(20),
    "disp_order" int,
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);

INSERT INTO public.m_shop_type
(id, corporation_group, name, disp_order, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES(1,'001'::character varying, '直営', 1, NOW(), NOW(), '', '', false);
INSERT INTO public.m_shop_type
(id, corporation_group, name, disp_order, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES(2,'001'::character varying, '直営（商店街)', 2, NOW(), NOW(), '', '', false);
INSERT INTO public.m_shop_type
(id, corporation_group, name, disp_order, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES(3,'001'::character varying, 'VSPA', 3, NOW(), NOW(), '', '', false);
INSERT INTO public.m_shop_type
(id, corporation_group, name, disp_order, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES(4,'001'::character varying, 'ストア飲食', 4, NOW(), NOW(), '', '', false);
INSERT INTO public.m_shop_type
(id, corporation_group, name, disp_order, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES(5,'001'::character varying, 'スポット', 5, NOW(), NOW(), '', '', false);
INSERT INTO public.m_shop_type
(id, corporation_group, name, disp_order, created_datetime, update_datetime, created_account_code, update_account_code, is_deleted)
VALUES(11,'001'::character varying, 'FC', 11, NOW(), NOW(), '', '', false);