-- Table: public.dim_date

-- DROP TABLE IF EXISTS public.dim_date;

CREATE TABLE IF NOT EXISTS public.dim_date
(
    date_key date NOT NULL,
    date_month integer NOT NULL,
    date_quarter integer NOT NULL,
    date_year integer NOT NULL,
    CONSTRAINT dim_date_pkey PRIMARY KEY (date_key)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dim_date
    OWNER to postgres;