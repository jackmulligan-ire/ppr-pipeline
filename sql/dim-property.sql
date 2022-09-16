with unique_combos as (
	select distinct description_of_property, not_full_market_price, vat_exclusive, property_size_description 
	from staging
)

INSERT INTO dim_property (
    property_key, 
    property_build_type, 
    property_sale_type, 
    property_vat_exclusive, 
    property_size)
SELECT
    ROW_NUMBER () OVER (ORDER BY description_of_property),
	description_of_property, 
	not_full_market_price, 
	vat_exclusive, 
	property_size_description
FROM 
	unique_combos;
