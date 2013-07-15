--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- Name: restaurant; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE restaurant2 (
    id integer NOT NULL,
    address character varying(255),
    applicationdate timestamp without time zone,
    area character varying(255),
    avgprice real NOT NULL,
    name character varying(255),
    state character varying(255),
    telephone character varying(255),
    timerange character varying(255),
    website character varying(255),
    registeruser_id integer,
    accesscount bigint,
    highlighted boolean
);


ALTER TABLE public.restaurant2 OWNER TO paw;

--
-- Name: restaurant_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE restaurant2_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.restaurant2_id_seq OWNER TO paw;

--
-- Name: restaurant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE restaurant2_id_seq OWNED BY restaurant2.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY restaurant2 ALTER COLUMN id SET DEFAULT nextval('restaurant2_id_seq'::regclass);


--
-- PostgreSQL database dump complete
--

--
-- Name: systemuser; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE systemuser2 (
    id integer NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    registerdate timestamp without time zone,
    surname character varying(255),
    type character varying(255),
    username character varying(255),
    avatar_id integer,
    token character varying(255)
);


ALTER TABLE public.systemuser2 OWNER TO paw;

--
-- Name: systemuser2_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE systemuser3_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.systemuser3_id_seq OWNER TO paw;

--
-- Name: systemuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE systemuser3_id_seq OWNED BY systemuser2.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY systemuser2 ALTER COLUMN id SET DEFAULT nextval('systemuser3_id_seq'::regclass);


--
-- PostgreSQL database dump complete
--


--
-- Name: dailyreport; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE dailyreport (
    id integer NOT NULL,
    date timestamp without time zone,
    highlightclicks integer NOT NULL,
    highlightshows integer NOT NULL,
    restaurant_id integer
);


ALTER TABLE public.dailyreport OWNER TO paw;

--
-- Name: dailyreport_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE dailyreport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dailyreport_id_seq OWNER TO paw;

--
-- Name: dailyreport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE dailyreport_id_seq OWNED BY dailyreport.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY dailyreport ALTER COLUMN id SET DEFAULT nextval('dailyreport_id_seq'::regclass);


--
-- PostgreSQL database dump complete
--

--
-- Name: likes; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE likes2 (
    userlikes_id integer NOT NULL,
    likes_id integer NOT NULL
);

ALTER TABLE public.likes2 OWNER TO paw;	

--
-- Name: rating; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE rating2 (
    id integer NOT NULL,
    comment character varying(255),
    date timestamp without time zone,
    score integer,
    restaurant_id integer,
    user_id integer
);


ALTER TABLE public.rating2 OWNER TO paw;

--
-- Name: rating_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--


CREATE SEQUENCE rating2_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.rating2_id_seq OWNER TO paw;

--
-- Name: rating_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE rating2_id_seq OWNED BY rating2.id;

--
-- Name: restaurant_foodtype; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE restaurant_foodtype2 (
    restaurants_id integer NOT NULL,
    foodtypes_id integer NOT NULL
);


ALTER TABLE public.restaurant_foodtype2 OWNER TO paw;

--
-- Name: unlikes; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE unlikes2 (
    userunlikes_id integer NOT NULL,
    unlikes_id integer NOT NULL
);


ALTER TABLE public.unlikes2 OWNER TO paw;


INSERT INTO likes2 SELECT * FROM likes;
INSERT INTO unlikes2 SELECT * FROM unlikes;
INSERT INTO rating2 SELECT * FROM rating;
INSERT INTO restaurant_foodtype2 SELECT * FROM restaurant_foodtype;
INSERT INTO restaurant2 (id, address, applicationdate, area, avgprice, name, state, telephone, timerange, website, accesscount, highlighted) SELECT id, address, current_date, area, avgprice, name, 'Accepted' AS state, telephone, timerange, website, 0 as accesscount, FALSE as highlighted FROM restaurant;
INSERT INTO systemuser2 SELECT * FROM systemuser;

DROP TABLE likes;
DROP TABLE unlikes;
DROP TABLE rating;
DROP TABLE restaurant_foodtype;
DROP TABLE restaurant;
DROP TABLE systemuser;
ALTER TABLE systemuser2 RENAME TO systemuser;
ALTER TABLE restaurant2 RENAME TO restaurant;
ALTER TABLE likes2 RENAME TO likes;
ALTER TABLE unlikes2 RENAME TO unlikes;
ALTER TABLE rating2 RENAME TO rating;
ALTER TABLE restaurant_foodtype2 RENAME TO restaurant_foodtype;



SELECT setval('systemuser3_id_seq', (SELECT MAX(id) FROM systemuser));
SELECT setval('restaurant2_id_seq', (SELECT MAX(id) FROM restaurant));
SELECT setval('rating2_id_seq', (SELECT MAX(id) FROM rating));

ALTER SEQUENCE systemuser3_id_seq RENAME TO systemuser_id_seq;
ALTER SEQUENCE rating2_id_seq RENAME TO rating_id_seq;
ALTER SEQUENCE restaurant2_id_seq RENAME TO restaurant_id_seq;


ALTER TABLE ONLY rating ALTER COLUMN id SET DEFAULT nextval('rating_id_seq'::regclass);