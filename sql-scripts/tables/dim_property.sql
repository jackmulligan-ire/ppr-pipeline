-- Table: public.dim_property

-- DROP TABLE IF EXISTS public.dim_property;

CREATE TABLE IF NOT EXISTS public.dim_property
(
    property_key integer NOT NULL DEFAULT nextval('dim_property_property_key_seq'::regclass),
    property_build_type character varying(50) COLLATE pg_catalog."default" NOT NULL,
    property_vat_exclusive character varying(3) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dim_property_pkey PRIMARY KEY (property_key)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dim_property
    OWNER to postgres;