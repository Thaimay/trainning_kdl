ALTER TABLE project_schedule ALTER COLUMN schedule_date DROP NOT NULL;

ALTER TABLE project_plan ADD COLUMN participating_store_corporation_id bigint;
