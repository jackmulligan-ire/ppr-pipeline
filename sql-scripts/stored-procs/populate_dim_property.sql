-- PROCEDURE: public.populate_dim_property()

-- DROP PROCEDURE IF EXISTS public.populate_dim_property();

CREATE OR REPLACE PROCEDURE public.populate_dim_property(
	)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
DELETE FROM dim_property;

with dim_property_combos as (
	select distinct description_of_property, vat_exclusive
	from staging
)

INSERT INTO dim_property (
    property_key, 
    property_build_type, 
    property_vat_exclusive 
   )
SELECT
    ROW_NUMBER () OVER (ORDER BY description_of_property),
	description_of_property, 
	vat_exclusive 
FROM 
	dim_property_combos;
END;
$BODY$;
ALTER PROCEDURE public.populate_dim_property()
    OWNER TO postgres;
