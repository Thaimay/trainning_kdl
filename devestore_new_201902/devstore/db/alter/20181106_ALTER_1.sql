ALTER TABLE public.shop ADD column rent_start_date date;
ALTER TABLE public.shop ADD column rent_end_date date;
ALTER TABLE public.shop ADD column rent_year double precision;

ALTER TABLE public.shop_history ADD column rent_start_date date;
ALTER TABLE public.shop_history ADD column rent_end_date date;
ALTER TABLE public.shop_history ADD column rent_year double precision;