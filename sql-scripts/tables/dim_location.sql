-- Table: public.dim_location

-- DROP TABLE IF EXISTS public.dim_location;

CREATE TABLE IF NOT EXISTS public.dim_location
(
    location_key integer NOT NULL DEFAULT nextval('dim_location_location_key_seq'::regclass),
    location_county character varying(20) COLLATE pg_catalog."default" NOT NULL,
    location_nuts_3 character varying(20) COLLATE pg_catalog."default" NOT NULL,
    location_province character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dim_location_pkey PRIMARY KEY (location_key)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dim_location
    OWNER to postgres;