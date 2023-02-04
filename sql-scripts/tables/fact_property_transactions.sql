-- Table: public.fact_property_transactions

-- DROP TABLE IF EXISTS public.fact_property_transactions;

CREATE TABLE IF NOT EXISTS public.fact_property_transactions
(
    date_key date NOT NULL,
    location_key integer NOT NULL,
    property_key integer NOT NULL,
    transaction_price numeric(11,2) NOT NULL,
    id integer NOT NULL DEFAULT nextval('fact_property_transactions_id_seq'::regclass),
    CONSTRAINT fact_property_transactions_pkey PRIMARY KEY (id),
    CONSTRAINT date_key FOREIGN KEY (date_key)
        REFERENCES public.dim_date (date_key) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT location_key FOREIGN KEY (location_key)
        REFERENCES public.dim_location (location_key) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT property_key FOREIGN KEY (property_key)
        REFERENCES public.dim_property (property_key) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.fact_property_transactions
    OWNER to postgres;