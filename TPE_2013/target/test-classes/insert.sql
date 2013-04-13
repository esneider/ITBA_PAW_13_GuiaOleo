INSERT INTO foodtypes (id, name, ammount) VALUES (4, 'Japones', 3);
INSERT INTO foodtypes (id, name, ammount) VALUES (6, 'Afrodisiaca', 12);
INSERT INTO foodtypes (id, name, ammount) VALUES (8, 'Cocina Italiana', 1);
INSERT INTO foodtypes (id, name, ammount) VALUES (9, 'Cocina de Autor', 6);
INSERT INTO foodtypes (id, name, ammount) VALUES (10, 'Vegetariana', 8);
INSERT INTO foodtypes (id, name, ammount) VALUES (5, 'Mexicana', 2);
INSERT INTO foodtypes (id, name, ammount) VALUES (3, 'Chino', 26);
INSERT INTO foodtypes (id, name, ammount) VALUES (1, 'Parrilla', 187);
INSERT INTO foodtypes (id, name, ammount) VALUES (2, 'Pastas', 66);
INSERT INTO foodtypes (id, name, ammount) VALUES (7, 'Tenedor Libre', 45);
INSERT INTO foodtypes (id, name, ammount) VALUES (11, 'Variada', 102);
INSERT INTO foodtypes (id, name, ammount) VALUES (12, 'Comida Rapida', 264);
INSERT INTO foodtypes (id, name, ammount) VALUES (13, 'Pizza', 198);
INSERT INTO foodtypes (id, name, ammount) VALUES (14, 'Sushi', 67);

INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (1, 'Rodizio', 'Puerto Madero', 'Puerto Madero', '4509-2201', 'www.careta.com', '5 a 7', 450, 1, 9.8000000000000007, 102);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (2, 'MC Donalds', 'Alem y Corrientes', 'Centro', '4555-5555', 'www.mc.com', '10 a 22', 30, 12, 4.2000000000000002, 2096);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (3, 'Burger King', 'Florida y Corrientes', 'Centro', '4555-6554', 'www.burger.com', '12 a 22', 25, 12, 4.2999999999999998, 1732);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (4, 'Maizales', 'Acoyte 2244', 'Caballito', '4585-3434', 'www.maizales.com', '11 a 16', 85, 7, 7.5, 698);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (5, 'Subway', 'Tucuman 504', 'Centro', '5555-2144', 'www.subway.com', '14 a 24', 40, 12, 6, 2911);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (6, 'Kentucky', 'Godoy Cruz y Sta Fe', 'Palermo', '4503-1233', 'www.kentucky.com', '08 a 23', 40, 13, 7.9000000000000004, 350);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (7, 'La rosadita', 'Gorriti 4003', 'Palermo', '4503-2332', 'www.larosadita.com', '12 a 18', 75, 11, 8.4000000000000004, 24);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (8, 'El club de la milanesa', 'Bonpland 3290', 'Palermo', '4503-3256', 'www.cblm.com', '06 a 12', 70, 11, 8.8000000000000007, 209);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (9, 'Sushi Pop', 'Cabildo 3044', 'Belgrano', '4333-1233', 'www.sushipop.com', '18 a 23', 120, 14, 8, 1006);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (10, 'Cocina Japonesa', 'Corrientes y Scalabrini Ortiz', 'Villa Crespo', '4856-1250', 'www.cj.com', '06 a 19', 70, 4, 4.2999999999999998, 8);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (12, 'Los platitos', 'Costanera 203', 'Costanera', '4553-1540', 'www.lp.com', '08 a 24', 130, 1, 9.3000000000000007, 40);
INSERT INTO restaurants (id, name, address, area, telephone, website, timerange, avgprice, foodtypeid, avgscore, cantratings) VALUES (11, 'Cocina China', 'San Martin 2990', 'Agronomia', '4667-1255', 'www.cc.com', '08 a 23', 40, 3, 2.1000000000000001, 32);

INSERT INTO users (id, name, surname, mail, username, password, pictureid) VALUES (1, 'paw', 'paw', 'paw@paw.paw', 'paw', 'paw                                                             ', NULL);
INSERT INTO users (id, name, surname, mail, username, password, pictureid) VALUES (2, 'alan', 'alan', 'alan@alan.alan', 'alan', 'alan                                                            ', NULL);
INSERT INTO users (id, name, surname, mail, username, password, pictureid) VALUES (3, 'dario', 'dario', 'dario@dario.dario', 'dario', 'dario                                                           ', NULL);
INSERT INTO users (id, name, surname, mail, username, password, pictureid) VALUES (4, 'lucas', 'lucas', 'lucas@lucas.lucas', 'lucas', 'lucas                                                           ', NULL);

INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (7, 3, 'Muy bueno', 1, 8);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (11, 1, 'Malisimo', 1, 2);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (13, 4, 'Bueno', 1, 6);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (15, 3, 'Medio feo', 1, 7);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (17, 3, 'Muy rico', 1, 10);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (19, 1, 'Malisimo, muy insano', 1, 5);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (20, 3, 'No me gusto tanto', 4, 6);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (21, 5, 'Geniaal', 4, 3);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (22, 2, 'Muy chino', 4, 11);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (23, 1, 'No me gusto para nada', 2, 6);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (24, 5, 'Mooi bueno', 2, 8);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (25, 3, 'Maso', 3, 11);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (26, 2, 'Burguer no me gusta', 3, 3);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (27, 1, 'Malisimo', 3, 6);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (28, 5, 'Excelente', 3, 12);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (29, 3, 'Me guseto un poco', 3, 5);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (30, 4, 'Like', 3, 1);
INSERT INTO ratings (id, score, comment, userid, restaurantid) VALUES (31, 2, 'No me gusta mucho el sushi', 3, 9);


