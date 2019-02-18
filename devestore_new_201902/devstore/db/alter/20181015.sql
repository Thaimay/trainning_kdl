ALTER TABLE public.project ALTER COLUMN building_expected_value TYPE int8 USING building_expected_value::int8;
ALTER TABLE public.project ALTER COLUMN sales_prediction TYPE int8 USING sales_prediction::int8;

ALTER TABLE public.project_history ALTER COLUMN building_expected_value TYPE int8 USING building_expected_value::int8;
ALTER TABLE public.project_history ALTER COLUMN sales_prediction TYPE int8 USING sales_prediction::int8;