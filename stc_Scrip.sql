--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2023-02-25 16:54:55

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
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16445)
-- Name: Permission_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Permission_groups" (
    id integer NOT NULL,
    group_name character varying
);


ALTER TABLE public."Permission_groups" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16443)
-- Name: Permission_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."Permission_groups" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Permission_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 207 (class 1259 OID 16506)
-- Name: files; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.files (
    id integer NOT NULL,
    content_type character varying(255),
    data oid,
    name character varying(255),
    size bigint,
    item_id integer
);


ALTER TABLE public.files OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16504)
-- Name: files_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.files ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.files_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 201 (class 1259 OID 16426)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id integer NOT NULL,
    type character varying,
    name character varying,
    permission_group_id integer,
    parent_id integer
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16424)
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.item ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 205 (class 1259 OID 16453)
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissions (
    id integer NOT NULL,
    user_email character varying,
    group_id integer,
    permission_level character varying
);


ALTER TABLE public.permissions OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16451)
-- Name: permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.permissions ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3014 (class 0 OID 16445)
-- Dependencies: 203
-- Data for Name: Permission_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Permission_groups" (id, group_name) FROM stdin;
1	admin
2	user
\.


--
-- TOC entry 3018 (class 0 OID 16506)
-- Dependencies: 207
-- Data for Name: files; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.files (id, content_type, data, name, size, item_id) FROM stdin;
\.


--
-- TOC entry 3012 (class 0 OID 16426)
-- Dependencies: 201
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item (id, type, name, permission_group_id, parent_id) FROM stdin;
1	j	\N	\N	\N
4	\N	spaceName	1	\N
5	Space	spaceName	1	\N
6	Space	spaceName	1	\N
7	Folder	ddd	1	5
8	Folder	ddd	1	5
9	Folder	ddd123	1	5
10	File	ww	1	9
11	Folder	ddd123	1	5
12	File	ww	1	9
13	File	ww	1	9
14	File	ww	1	9
15	File	ww	1	9
16	File	ww	1	9
17	File	ww	1	9
18	File	ww	1	9
19	File	ww	1	9
20	File	w2222w	1	9
21	File	w2222w	1	9
\.


--
-- TOC entry 3016 (class 0 OID 16453)
-- Dependencies: 205
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissions (id, user_email, group_id, permission_level) FROM stdin;
1	admin	1	edit
2	user	2	view
\.


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 202
-- Name: Permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Permission_id_seq"', 2, true);


--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 206
-- Name: files_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.files_id_seq', 1, false);


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 200
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_id_seq', 21, true);


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 204
-- Name: permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permissions_id_seq', 2, true);


--
-- TOC entry 2879 (class 2606 OID 16513)
-- Name: files files_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);


--
-- TOC entry 2873 (class 2606 OID 16462)
-- Name: item item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pk PRIMARY KEY (id);


--
-- TOC entry 2875 (class 2606 OID 16460)
-- Name: Permission_groups permission_groups_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Permission_groups"
    ADD CONSTRAINT permission_groups_pk PRIMARY KEY (id);


--
-- TOC entry 2877 (class 2606 OID 16464)
-- Name: permissions permissions_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pk PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 16472)
-- Name: permissions permissions_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_fk FOREIGN KEY (group_id) REFERENCES public."Permission_groups"(id);


-- Completed on 2023-02-25 16:55:00

--
-- PostgreSQL database dump complete
--

