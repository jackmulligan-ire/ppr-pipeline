-- PROCEDURE: public.populate_dim_date()

-- DROP PROCEDURE IF EXISTS public.populate_dim_date();

CREATE OR REPLACE PROCEDURE public.populate_dim_date(
	)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
DELETE FROM public.dim_date;

INSERT INTO public.dim_date (date_key, date_month, date_quarter, date_year)
SELECT DISTINCT 
TO_DATE(public.staging.date_of_sale, 'DD/MM/YYYY') as formatted_date,
EXTRACT(MONTH FROM TO_DATE(public.staging.date_of_sale, 'DD/MM/YYYY')),
EXTRACT(QUARTER FROM TO_DATE(public.staging.date_of_sale, 'DD/MM/YYYY')),
EXTRACT(YEAR from TO_DATE(public.staging.date_of_sale, 'DD/MM/YYYY'))
FROM public.staging ORDER BY formatted_date;
END;
$BODY$;
ALTER PROCEDURE public.populate_dim_date()
    OWNER TO postgres;
