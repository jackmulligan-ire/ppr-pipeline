INSERT INTO 
	fact_property_transactions
	(date_key, location_key, property_key, transaction_price)
SELECT
	d.date_key,
	l.location_key,
	pr.property_key,
	translate(s.price, ',', '')::NUMERIC(11,2)
FROM
	staging as s
	JOIN 
	dim_date as d on TO_DATE(s.date_of_sale, 'DD/MM/YYYY') = d.date_key
	JOIN 
	dim_location as l on s.county = l.location_county
	JOIN 
	dim_property as pr on s.description_of_property = pr.property_build_type
	and FLIP_YES_NO(s.not_full_market_price) = pr.property_market_price
	and s.vat_exclusive = pr.property_vat_exclusive
	and s.property_size_description = pr.property_size;