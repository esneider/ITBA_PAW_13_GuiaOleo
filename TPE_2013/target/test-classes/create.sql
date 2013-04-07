DROP TABLE IF EXISTS users, pictures, friendRequests, friends, notifications, statuses, likes;

GRANT ALL PRIVILEGES ON DATABASE paw4 to paw;



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
    email varchar(50),
    birth date,
    username varchar(50),
    password char(64),
    pictureId integer,

    PRIMARY KEY(id),
    FOREIGN KEY(pictureId) REFERENCES pictures(id)
);

CREATE UNIQUE INDEX indexUsersId on users(id);

CREATE UNIQUE INDEX indexUsersUsername on users(username);

GRANT ALL PRIVILEGES ON TABLE users to paw;

GRANT ALL PRIVILEGES ON TABLE users_id_seq to paw;



CREATE TABLE friendRequests
(
    id serial,
    fromUserId integer,
    toUserId integer,
    time timestamp,

    PRIMARY KEY(id),
    FOREIGN KEY(fromUserId) REFERENCES users(id),
    FOREIGN KEY(toUserId) REFERENCES users(id)
);

CREATE UNIQUE INDEX indexFriendRequestsId on friendRequests(id);

CREATE UNIQUE INDEX indexFriendRequestsFromUserId on friendRequests(fromUserId);

CREATE UNIQUE INDEX indexFriendRequestsToUserId on friendRequests(toUserId);

GRANT ALL PRIVILEGES ON TABLE friendRequests to paw;

GRANT ALL PRIVILEGES ON TABLE friendRequests_id_seq to paw;



CREATE TABLE friends
(
    id serial,
    userId integer,
    withUserId integer,

    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES users(id),
    FOREIGN KEY(withUserId) REFERENCES users(id)
);

CREATE UNIQUE INDEX indexFriendsId on friends(id);

CREATE UNIQUE INDEX indexFriendsUserId on friends(userId);

GRANT ALL PRIVILEGES ON TABLE friends to paw;

GRANT ALL PRIVILEGES ON TABLE friends_id_seq to paw;



CREATE TABLE notifications
(
    id serial,
    fromUserId integer,
    toUserId integer,
    time timestamp,
    type smallint,

    PRIMARY KEY(id),
    FOREIGN KEY(fromUserId) REFERENCES users(id),
    FOREIGN KEY(toUserId) REFERENCES users(id)
);

CREATE UNIQUE INDEX indexNotificationsId on notifications(id);

CREATE UNIQUE INDEX indexNotificationsToUserId on notifications(toUserId);

GRANT ALL PRIVILEGES ON TABLE notifications to paw;

GRANT ALL PRIVILEGES ON TABLE notifications_id_seq to paw;



CREATE TABLE statuses
(
    id serial,
    userId integer,
    text text,
    time timestamp,

    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES users(id)
);

CREATE UNIQUE INDEX indexStatusesId on statuses(id);

CREATE UNIQUE INDEX indexStatusesUserId on statuses(userId);

GRANT ALL PRIVILEGES ON TABLE statuses to paw;

GRANT ALL PRIVILEGES ON TABLE statuses_id_seq to paw;



CREATE TABLE likes
(
    id serial,
    userId integer,
    time timestamp,
    statusId integer,

    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES users(id),
    FOREIGN KEY(statusId) REFERENCES statuses(id)
);

CREATE UNIQUE INDEX indexLikesId on likes(id);

CREATE UNIQUE INDEX indexLikesStatusId on likes(statusId);

GRANT ALL PRIVILEGES ON TABLE likes to paw;

GRANT ALL PRIVILEGES ON TABLE likes_id_seq to paw;

