ALTER TABLE public.project ADD implementation_schedule_datetime date NULL;
ALTER TABLE public.project ADD shop_name_temporary varchar(256) NULL;

ALTER TABLE public.project_history ADD implementation_schedule_datetime date NULL;
ALTER TABLE public.project_history ADD shop_name_temporary varchar(256) NULL;