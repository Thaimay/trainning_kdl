update project set building_expected_value = null;
update project set sales_prediction = null;
update project_history set building_expected_value = null;
update project_history set sales_prediction = null;

ALTER TABLE public.project ALTER COLUMN building_expected_value TYPE double precision USING building_expected_value::double precision;
ALTER TABLE public.project ALTER COLUMN sales_prediction TYPE double precision USING sales_prediction::double precision;

ALTER TABLE public.project_history ALTER COLUMN building_expected_value TYPE double precision USING building_expected_value::double precision;
ALTER TABLE public.project_history ALTER COLUMN sales_prediction TYPE double precision USING sales_prediction::double precision;

ALTER TABLE project_contract_progress_history ALTER COLUMN contract_number_of_year type double precision;