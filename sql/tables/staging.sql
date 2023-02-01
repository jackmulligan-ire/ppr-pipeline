-- Table: public.staging

-- DROP TABLE IF EXISTS public.staging;

CREATE TABLE IF NOT EXISTS public.staging
(
    address text COLLATE pg_catalog."default" NOT NULL,
    county text COLLATE pg_catalog."default" NOT NULL,
    eircode text COLLATE pg_catalog."default",
    price text COLLATE pg_catalog."default" NOT NULL,
    not_full_market_price text COLLATE pg_catalog."default" NOT NULL,
    vat_exclusive text COLLATE pg_catalog."default" NOT NULL,
    description_of_property text COLLATE pg_catalog."default",
    property_size_description text COLLATE pg_catalog."default",
    date_of_sale text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT staging_pkey PRIMARY KEY (date_of_sale, address, price)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.staging
    OWNER to postgres;