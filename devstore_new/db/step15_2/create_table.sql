CREATE TABLE IF NOT EXISTS I_SHOP_ADMIN(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "shop_admin_id" bigint,
    "shop_id" bigint,
    "deposit" numeric(12,0),
    "rent_brand_cd" varchar(38),
    "directly_operated_house_flag" varchar(2),
    "opening_settlement_no" varchar(15),
    "interior_cost" numeric(12,0),
    "equipment_expenses" numeric(12,0),
    "burden_money" numeric(12,0),
    "management_expenses" numeric(12,0),
    "vmd_cost" numeric(12,0),
    "opening_cost_sum" numeric(12,0),
    "own_interior_cost" numeric(12,0),
    "own_equipment_expenses" numeric(12,0),
    "own_burden_money" numeric(12,0),
    "own_management_expenses" numeric(12,0),
    "own_vmd_cost" numeric(12,0),
    "own_opening_cost_sum" numeric(12,0),
    "closing_settlement_no" varchar(15),
    "closing_reason" varchar(40),
    "closing_reason_1" numeric(38,0),
    "closing_reason_2" numeric(38,0),
    "closing_reason_3" numeric(38,0),
    "current_section_status" numeric(38,0),
    "alternative_opening_division_id" numeric(38,0),
    "real_closing_flag" varchar(2),
    "loss_on_retirement_income" numeric(12,0),
    "loss_on_retirement_accounting" numeric(12,0),
    "cancellation_money" numeric(12,0),
    "current_status_recovery_money" numeric(12,0),
    "penalty" numeric(12,0),
    "other_expenses" numeric(12,0),
    "closing_cost_sum" numeric(12,0),
    "own_closing_cost_sum" numeric(12,0),
    "contents_replacement" varchar(2),
    "shop_other_flag" varchar(2),
    "shop_type_id" numeric(38,0),
    "shop_count" varchar(2),
    "brand_income_unit_id" numeric(38,0),
    "coordination_created_datetime" timestamp,
    "coordination_created_account_code" varchar(8),
    "coordination_update_datetime" timestamp,
    "coordination_update_account_code" varchar(8),
    "action" varchar(2),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
CREATE TABLE IF NOT EXISTS M_SHOP_COMPANY_ABBREVIATION(
    "id" serial primary key,
    "corporation_group" varchar(4) NOT NULL DEFAULT '001',
    "company_cd" varchar(6),
    "abbreviated_company_cd"  varchar(6),
    "abbreviated_company_name" varchar(16),
    "company_name" varchar(40),
    "created_datetime" timestamp NOT NULL,
    "update_datetime" timestamp NOT NULL,
    "created_account_code" varchar(64) NOT NULL,
    "update_account_code" varchar(64) NOT NULL,
    "is_deleted" boolean NOT NULL DEFAULT FALSE
);
