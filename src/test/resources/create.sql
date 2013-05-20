DROP TABLE IF EXISTS users, pictures, restaurants, ratings, foodtypes;
DROP TABLE IF EXISTS user, picture, restaurant, rating, foodtype;
GRANT ALL PRIVILEGES ON DATABASE paw2 to paw;



CREATE TABLE picture
(
    id serial,
    mime varchar(50),
    data bytea,

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX indexPictureId on picture(id);

GRANT ALL PRIVILEGES ON TABLE picture to paw;

GRANT ALL PRIVILEGES ON TABLE picture_id_seq to paw;



CREATE TABLE user
(
    id serial,
    name varchar(50),
    surname varchar(50),
    mail varchar(50),
    username varchar(50),
    password char(64),
    pictureId integer,

    PRIMARY KEY(id),
    FOREIGN KEY(pictureId) REFERENCES picture(id)
);

CREATE UNIQUE INDEX indexUserId on user(id);

CREATE UNIQUE INDEX indexUserUsername on user(username);

GRANT ALL PRIVILEGES ON TABLE user to paw;

GRANT ALL PRIVILEGES ON TABLE user_id_seq to paw;


CREATE TABLE foodType
(
    id serial,
    name varchar(100),
    ammount integer,

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX foodTypeId on foodType(id);

GRANT ALL PRIVILEGES ON TABLE foodType to paw;

GRANT ALL PRIVILEGES ON TABLE foodType_id_seq to paw;


CREATE TABLE restaurant
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
    FOREIGN KEY(foodTypeId) REFERENCES foodType(id)
);

CREATE UNIQUE INDEX indexRestaurantId on restaurant(id);

GRANT ALL PRIVILEGES ON TABLE restaurant to paw;

GRANT ALL PRIVILEGES ON TABLE restaurant_id_seq to paw;



CREATE TABLE rating
(
    id serial,
    score integer,
    comment varchar(1000),
    userId integer,
    restaurantId integer,
    ratingDate date,

    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES user(id),
    FOREIGN KEY(restaurantId) REFERENCES restaurant(id)
);

CREATE UNIQUE INDEX ratingsId on rating(id);

GRANT ALL PRIVILEGES ON TABLE rating to paw;

GRANT ALL PRIVILEGES ON TABLE rating_id_seq to paw;

ALTER DATABASE paw2 SET bytea_output = 'escape';

