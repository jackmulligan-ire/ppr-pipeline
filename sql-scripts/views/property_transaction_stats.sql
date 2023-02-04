-- View: public.property_transaction_stats

-- DROP VIEW public.property_transaction_stats;

CREATE OR REPLACE VIEW public.property_transaction_stats
 AS
 SELECT dl.location_county,
    dd.date_year,
    dd.date_month,
    percentile_cont(0.5::double precision) WITHIN GROUP (ORDER BY (fpt.transaction_price::double precision)) AS median_sale_price,
    avg(fpt.transaction_price) AS mean_sale_price,
    count(fpt.transaction_price) AS num_of_transactions
   FROM fact_property_transactions fpt
     JOIN dim_location dl ON fpt.location_key = dl.location_key
     JOIN dim_date dd ON fpt.date_key = dd.date_key
  GROUP BY dl.location_county, dd.date_year, dd.date_month
  ORDER BY dl.location_county, dd.date_year, dd.date_month;

ALTER TABLE public.property_transaction_stats
    OWNER TO postgres;

