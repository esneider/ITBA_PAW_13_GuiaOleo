--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.3
-- Dumped by pg_dump version 9.2.3
-- Started on 2013-05-27 20:57:53

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 181 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 181
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 36868)
-- Name: foodtype; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE foodtype (
    id integer NOT NULL,
    name character varying(255),
    
    PRIMARY KEY(id)
);


ALTER TABLE public.foodtype OWNER TO paw;

--
-- TOC entry 168 (class 1259 OID 36866)
-- Name: foodtype_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE foodtype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.foodtype_id_seq OWNER TO paw;

--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 168
-- Name: foodtype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE foodtype_id_seq OWNED BY foodtype.id;


--
-- TOC entry 171 (class 1259 OID 36876)
-- Name: picture; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE picture (
    id integer NOT NULL,
    img bytea,
    mime character varying(255),
    
    PRIMARY KEY(id)
);


ALTER TABLE public.picture OWNER TO paw;

--
-- TOC entry 170 (class 1259 OID 36874)
-- Name: picture_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE picture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.picture_id_seq OWNER TO paw;

--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 170
-- Name: picture_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE picture_id_seq OWNED BY picture.id;

--
-- TOC entry 178 (class 1259 OID 36911)
-- Name: systemuser; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE systemuser (
    id integer NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    registerdate timestamp without time zone,
    surname character varying(255),
    type character varying(255),
    username character varying(255),
    avatar_id integer,
    
    PRIMARY KEY(id),
    UNIQUE(username),
    UNIQUE(email),
    FOREIGN KEY(avatar_id) REFERENCES picture(id)
);


ALTER TABLE public.systemuser OWNER TO paw;

--
-- TOC entry 177 (class 1259 OID 36909)
-- Name: systemuser_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE systemuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.systemuser_id_seq OWNER TO paw;

--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 177
-- Name: systemuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE systemuser_id_seq OWNED BY systemuser.id;



--
-- TOC entry 175 (class 1259 OID 36895)
-- Name: restaurant; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE restaurant (
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
    
    PRIMARY KEY(id),
    FOREIGN KEY(registeruser_id) REFERENCES systemuser(id)
);


ALTER TABLE public.restaurant OWNER TO paw;

--
-- TOC entry 176 (class 1259 OID 36904)
-- Name: restaurant_foodtype; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE restaurant_foodtype (
    restaurants_id integer NOT NULL,
    foodtypes_id integer NOT NULL,
    FOREIGN KEY(restaurants_id) REFERENCES restaurant(id),
    FOREIGN KEY(restaurants_id) REFERENCES foodtype(id)
);

ALTER TABLE public.restaurant_foodtype OWNER TO paw;

--
-- TOC entry 174 (class 1259 OID 36893)
-- Name: restaurant_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE restaurant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.restaurant_id_seq OWNER TO paw;

--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 174
-- Name: restaurant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE restaurant_id_seq OWNED BY restaurant.id;



--
-- TOC entry 173 (class 1259 OID 36887)
-- Name: rating; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE rating (
    id integer NOT NULL,
    comment character varying(255),
    date timestamp without time zone,
    score integer,
    restaurant_id integer,
    user_id integer,
    
    PRIMARY KEY(id),
    FOREIGN KEY(restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY(user_id) REFERENCES systemuser(id)
);


ALTER TABLE public.rating OWNER TO paw;

--
-- TOC entry 172 (class 1259 OID 36885)
-- Name: rating_id_seq; Type: SEQUENCE; Schema: public; Owner: paw
--

CREATE SEQUENCE rating_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rating_id_seq OWNER TO paw;

--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 172
-- Name: rating_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: paw
--

ALTER SEQUENCE rating_id_seq OWNED BY rating.id;


--
-- TOC entry 179 (class 1259 OID 36920)
-- Name: likes; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE likes (
    userlikes_id integer NOT NULL,
    likes_id integer NOT NULL,
    FOREIGN KEY(userlikes_id) REFERENCES rating(id),
    FOREIGN KEY(likes_id) REFERENCES systemuser(id)
);


ALTER TABLE public.likes OWNER TO paw;

--
-- TOC entry 180 (class 1259 OID 36925)
-- Name: unlikes; Type: TABLE; Schema: public; Owner: paw
--

CREATE TABLE unlikes (
    userUnlikes_id integer NOT NULL,
    unlikes_id integer NOT NULL,
    FOREIGN KEY(userunlikes_id) REFERENCES rating(id),
    FOREIGN KEY(unlikes_id) REFERENCES systemuser(id)
);


ALTER TABLE public.unlikes OWNER TO paw;

--
-- TOC entry 1955 (class 2604 OID 36871)
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY foodtype ALTER COLUMN id SET DEFAULT nextval('foodtype_id_seq'::regclass);


--
-- TOC entry 1956 (class 2604 OID 36879)
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY picture ALTER COLUMN id SET DEFAULT nextval('picture_id_seq'::regclass);


--
-- TOC entry 1957 (class 2604 OID 36890)
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY rating ALTER COLUMN id SET DEFAULT nextval('rating_id_seq'::regclass);


--
-- TOC entry 1958 (class 2604 OID 36898)
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY restaurant ALTER COLUMN id SET DEFAULT nextval('restaurant_id_seq'::regclass);


--
-- TOC entry 1959 (class 2604 OID 36914)
-- Name: id; Type: DEFAULT; Schema: public; Owner: paw
--

ALTER TABLE ONLY systemuser ALTER COLUMN id SET DEFAULT nextval('systemuser_id_seq'::regclass);


--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-05-27 20:57:53

--
-- PostgreSQL database dump complete
--

INSERT INTO foodType (id, name) SELECT id, name from foodTypes;
INSERT INTO restaurant (id, address, applicationdate, area, avgprice, name, state, telephone, timerange, website) SELECT id, address, current_date, area, avgprice, name, 'Accepted' AS state, telephone, timerange, website FROM restaurants;
INSERT INTO picture (id, mime, img) SELECT id, mime, data FROM pictures;
INSERT INTO systemuser (id, email, name, password, registerdate, surname, type, username, avatar_id) SELECT id, mail, name, password, current_date, surname, 'Normal', username, pictureid FROM users;
INSERT INTO rating (id, comment, date, score, restaurant_id, user_id) SELECT id, comment, ratingdate, score, restaurantid, userid FROM ratings;

DROP TABLE ratings;
DROP TABLE restaurants;
DROP TABLE users;
DROP TABLE foodTypes;
DROP TABLE pictures;

ALTER DATABASE paw2 SET bytea_output = 'escape';

SELECT setval('systemuser_id_seq', (SELECT MAX(id) FROM systemuser));
SELECT setval('rating_id_seq', (SELECT MAX(id) FROM rating));
SELECT setval('restaurant_id_seq', (SELECT MAX(id) FROM restaurant));
SELECT setval('picture_id_seq', (SELECT MAX(id) FROM picture));
SELECT setval('foodtype_id_seq', (SELECT MAX(id) FROM foodtype));