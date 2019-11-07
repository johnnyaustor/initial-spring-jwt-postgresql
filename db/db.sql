--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8 (Ubuntu 10.8-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.8 (Ubuntu 10.8-0ubuntu0.18.04.1)

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
-- Name: initial_spring_jwt; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA initial_spring_jwt;


ALTER SCHEMA initial_spring_jwt OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: users; Type: TABLE; Schema: initial_spring_jwt; Owner: postgres
--

CREATE TABLE initial_spring_jwt.users (
    id integer NOT NULL,
    full_name character varying(50),
    email character varying(100),
    phone character varying(20),
    password text,
    create_at timestamp without time zone,
    update_at timestamp without time zone
);


ALTER TABLE initial_spring_jwt.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: initial_spring_jwt; Owner: postgres
--

CREATE SEQUENCE initial_spring_jwt.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE initial_spring_jwt.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: initial_spring_jwt; Owner: postgres
--

ALTER SEQUENCE initial_spring_jwt.users_id_seq OWNED BY initial_spring_jwt.users.id;


--
-- Name: users id; Type: DEFAULT; Schema: initial_spring_jwt; Owner: postgres
--

ALTER TABLE ONLY initial_spring_jwt.users ALTER COLUMN id SET DEFAULT nextval('initial_spring_jwt.users_id_seq'::regclass);


--
-- Data for Name: users; Type: TABLE DATA; Schema: initial_spring_jwt; Owner: postgres
--

INSERT INTO initial_spring_jwt.users VALUES (1, 'john doe', 'john@jap.com', '0281781', '$2a$10$aAdDrXt/kOokt4QlhvGkHOiK5cgFzaiKPVjnDklxzAxMzS/pJ5Mxq', '2019-11-06 21:33:08.705', NULL);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: initial_spring_jwt; Owner: postgres
--

SELECT pg_catalog.setval('initial_spring_jwt.users_id_seq', 1, true);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: initial_spring_jwt; Owner: postgres
--

ALTER TABLE ONLY initial_spring_jwt.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: users_email_uindex; Type: INDEX; Schema: initial_spring_jwt; Owner: postgres
--

CREATE UNIQUE INDEX users_email_uindex ON initial_spring_jwt.users USING btree (email);


--
-- Name: users_phone_uindex; Type: INDEX; Schema: initial_spring_jwt; Owner: postgres
--

CREATE UNIQUE INDEX users_phone_uindex ON initial_spring_jwt.users USING btree (phone);


--
-- PostgreSQL database dump complete
--

