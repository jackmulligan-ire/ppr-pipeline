-- PROCEDURE: public.populate_fact()

-- DROP PROCEDURE IF EXISTS public.populate_fact();

CREATE OR REPLACE PROCEDURE public.populate_fact(
	)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN

PERFORM setval('fact_property_transactions_id_seq', 1, false);

INSERT INTO 
        public.fact_property_transactions
        (date_key, location_key, property_key, transaction_price)
SELECT
        d.date_key,
        l.location_key,
        pr.property_key,
        translate(s.price, ',', '')::NUMERIC(11,2)
FROM
        public.staging as s
        JOIN 
        public.dim_date as d on TO_DATE(s.date_of_sale, 'DD/MM/YYYY') = d.date_key
        JOIN 
        public.dim_location as l on s.county = l.location_county
        JOIN 
        public.dim_property as pr on s.description_of_property = pr.property_build_type
        and s.vat_exclusive = pr.property_vat_exclusive;
END;
$BODY$;
ALTER PROCEDURE public.populate_fact()
    OWNER TO postgres;
