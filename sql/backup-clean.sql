--
-- PostgreSQL database cluster dump
--

-- Started on 2022-07-18 21:09:15 IST

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:xgBVlCZsErWs57bhi98LoQ==$36ltPt5M+c+Sv3hEZI+oG4Jpcly8GfLbGhCSmvnwMVA=:C7rCILGly9VPRc1dgh8OrkvYjjIiF0BSHDZm1qm3wVE=';






--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-07-18 21:09:15 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2022-07-18 21:09:17 IST

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-07-18 21:09:17 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2022-07-18 21:09:18 IST

--
-- PostgreSQL database dump complete
--

--
-- Database "presentation" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-07-18 21:09:18 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3343 (class 1262 OID 16392)
-- Name: presentation; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE presentation WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE presentation OWNER TO postgres;

\connect presentation

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16393)
-- Name: dim_date; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dim_date (
    date_key date NOT NULL,
    month integer NOT NULL,
    quarter integer NOT NULL,
    year integer NOT NULL
);


ALTER TABLE public.dim_date OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16399)
-- Name: dim_location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dim_location (
    location_key integer NOT NULL,
    county character varying(20) NOT NULL,
    nuts_3 character varying(20) NOT NULL,
    province character varying(20) NOT NULL
);


ALTER TABLE public.dim_location OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16398)
-- Name: dim_location_location_key_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dim_location_location_key_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dim_location_location_key_seq OWNER TO postgres;

--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 210
-- Name: dim_location_location_key_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dim_location_location_key_seq OWNED BY public.dim_location.location_key;


--
-- TOC entry 213 (class 1259 OID 16406)
-- Name: dim_property; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dim_property (
    property_key integer NOT NULL,
    build_type character varying(20) NOT NULL,
    sale_type character varying(20) NOT NULL,
    vat_exclusive character varying(3) NOT NULL,
    property_size character varying(50) NOT NULL
);


ALTER TABLE public.dim_property OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16405)
-- Name: dim_property_property_key_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dim_property_property_key_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dim_property_property_key_seq OWNER TO postgres;

--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 212
-- Name: dim_property_property_key_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dim_property_property_key_seq OWNED BY public.dim_property.property_key;


--
-- TOC entry 214 (class 1259 OID 16412)
-- Name: fact_property_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fact_property_transactions (
    date_key date NOT NULL,
    location_key integer NOT NULL,
    property_key integer NOT NULL,
    transaction_price numeric(11,2) NOT NULL
);


ALTER TABLE public.fact_property_transactions OWNER TO postgres;

--
-- TOC entry 3180 (class 2604 OID 16402)
-- Name: dim_location location_key; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dim_location ALTER COLUMN location_key SET DEFAULT nextval('public.dim_location_location_key_seq'::regclass);


--
-- TOC entry 3181 (class 2604 OID 16409)
-- Name: dim_property property_key; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dim_property ALTER COLUMN property_key SET DEFAULT nextval('public.dim_property_property_key_seq'::regclass);


--
-- TOC entry 3332 (class 0 OID 16393)
-- Dependencies: 209
-- Data for Name: dim_date; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dim_date (date_key, month, quarter, year) FROM stdin;
\.


--
-- TOC entry 3334 (class 0 OID 16399)
-- Dependencies: 211
-- Data for Name: dim_location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dim_location (location_key, county, nuts_3, province) FROM stdin;
\.


--
-- TOC entry 3336 (class 0 OID 16406)
-- Dependencies: 213
-- Data for Name: dim_property; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dim_property (property_key, build_type, sale_type, vat_exclusive, property_size) FROM stdin;
\.


--
-- TOC entry 3337 (class 0 OID 16412)
-- Dependencies: 214
-- Data for Name: fact_property_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fact_property_transactions (date_key, location_key, property_key, transaction_price) FROM stdin;
\.


--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 210
-- Name: dim_location_location_key_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dim_location_location_key_seq', 1, false);


--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 212
-- Name: dim_property_property_key_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dim_property_property_key_seq', 1, false);


--
-- TOC entry 3183 (class 2606 OID 16397)
-- Name: dim_date dim_date_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dim_date
    ADD CONSTRAINT dim_date_pkey PRIMARY KEY (date_key);


--
-- TOC entry 3185 (class 2606 OID 16404)
-- Name: dim_location dim_location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dim_location
    ADD CONSTRAINT dim_location_pkey PRIMARY KEY (location_key);


--
-- TOC entry 3187 (class 2606 OID 16411)
-- Name: dim_property dim_property_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dim_property
    ADD CONSTRAINT dim_property_pkey PRIMARY KEY (property_key);


--
-- TOC entry 3189 (class 2606 OID 16416)
-- Name: fact_property_transactions fact_property_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fact_property_transactions
    ADD CONSTRAINT fact_property_transactions_pkey PRIMARY KEY (date_key, location_key, property_key);


--
-- TOC entry 3190 (class 2606 OID 16417)
-- Name: fact_property_transactions date_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fact_property_transactions
    ADD CONSTRAINT date_key FOREIGN KEY (date_key) REFERENCES public.dim_date(date_key) NOT VALID;


--
-- TOC entry 3191 (class 2606 OID 16422)
-- Name: fact_property_transactions location_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fact_property_transactions
    ADD CONSTRAINT location_key FOREIGN KEY (location_key) REFERENCES public.dim_location(location_key) NOT VALID;


--
-- TOC entry 3192 (class 2606 OID 16427)
-- Name: fact_property_transactions property_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fact_property_transactions
    ADD CONSTRAINT property_key FOREIGN KEY (property_key) REFERENCES public.dim_property(property_key) NOT VALID;


-- Completed on 2022-07-18 21:09:19 IST

--
-- PostgreSQL database dump complete
--

--
-- Database "staging" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-07-18 21:09:19 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3313 (class 1262 OID 16384)
-- Name: staging; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE staging WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE staging OWNER TO postgres;

\connect staging

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16385)
-- Name: staging; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staging (
    address text NOT NULL,
    county text NOT NULL,
    eircode text,
    price text NOT NULL,
    not_full_market_price text NOT NULL,
    vat_exclusive text NOT NULL,
    description_of_property text,
    property_size_description text,
    date_of_sale text NOT NULL
);


ALTER TABLE public.staging OWNER TO postgres;

--
-- TOC entry 3307 (class 0 OID 16385)
-- Dependencies: 209
-- Data for Name: staging; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staging (address, county, eircode, price, not_full_market_price, vat_exclusive, description_of_property, property_size_description, date_of_sale) FROM stdin;
\.


--
-- TOC entry 3167 (class 2606 OID 16391)
-- Name: staging staging_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staging
    ADD CONSTRAINT staging_pkey PRIMARY KEY (date_of_sale, address, price);


-- Completed on 2022-07-18 21:09:19 IST

--
-- PostgreSQL database dump complete
--

-- Completed on 2022-07-18 21:09:19 IST

--
-- PostgreSQL database cluster dump complete
--

