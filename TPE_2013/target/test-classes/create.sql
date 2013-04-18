DROP TABLE IF EXISTS users, pictures, restaurants, ratings, foodtypes;
GRANT ALL PRIVILEGES ON DATABASE paw to paw;



CREATE TABLE pictures
(
    id serial,
    mime varchar(50),
    data bytea,

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX indexPicturesId on pictures(id);

GRANT ALL PRIVILEGES ON TABLE pictures to paw;

GRANT ALL PRIVILEGES ON TABLE pictures_id_seq to paw;



CREATE TABLE users
(
    id serial,
    name varchar(50),
    surname varchar(50),
    mail varchar(50),
    username varchar(50),
    password char(64),
    avatar integer,

    PRIMARY KEY(id),
    FOREIGN KEY(avatar) REFERENCES pictures(id)
);

CREATE UNIQUE INDEX indexUsersId on users(id);

CREATE UNIQUE INDEX indexUsersUsername on users(username);

GRANT ALL PRIVILEGES ON TABLE users to paw;

GRANT ALL PRIVILEGES ON TABLE users_id_seq to paw;


CREATE TABLE foodTypes
(
    id serial,
    name varchar(100),
    ammount integer,

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX foodTypesId on foodTypes(id);

GRANT ALL PRIVILEGES ON TABLE foodTypes to paw;

GRANT ALL PRIVILEGES ON TABLE foodTypes_id_seq to paw;


CREATE TABLE restaurants
(
    id serial,
    name varchar(50),
    address varchar(50),
    area varchar(50),
    telephone varchar(50),
    website varchar(100),
    timerange varchar(100),
    avgprice float,
    foodTypeId integer,
    avgScore float,
    cantRatings integer,

    PRIMARY KEY(id),
    FOREIGN KEY(foodTypeId) REFERENCES foodTypes(id)
);

CREATE UNIQUE INDEX indexRestaurantsId on restaurants(id);

GRANT ALL PRIVILEGES ON TABLE restaurants to paw;

GRANT ALL PRIVILEGES ON TABLE restaurants_id_seq to paw;



CREATE TABLE ratings
(
    id serial,
    score integer,
    comment varchar(1000),
    userId integer,
    restaurantId integer,
    ratingDate date,

    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES users(id),
    FOREIGN KEY(restaurantId) REFERENCES restaurants(id)
);

CREATE UNIQUE INDEX ratingsId on ratings(id);

GRANT ALL PRIVILEGES ON TABLE ratings to paw;

GRANT ALL PRIVILEGES ON TABLE ratings_id_seq to paw;

