INSERT IGNORE INTO vets
VALUES (1, 'James', 'Carter');
INSERT IGNORE INTO vets
VALUES (2, 'Helen', 'Leary');
INSERT IGNORE INTO vets
VALUES (3, 'Linda', 'Douglas');
INSERT IGNORE INTO vets
VALUES (4, 'Rafael', 'Ortega');
INSERT IGNORE INTO vets
VALUES (5, 'Henry', 'Stevens');
INSERT IGNORE INTO vets
VALUES (6, 'Sharon', 'Jenkins');

INSERT IGNORE INTO specialties
VALUES (1, 'radiology');
INSERT IGNORE INTO specialties
VALUES (2, 'surgery');
INSERT IGNORE INTO specialties
VALUES (3, 'dentistry');

INSERT IGNORE INTO vet_specialties
VALUES (2, 1);
INSERT IGNORE INTO vet_specialties
VALUES (3, 2);
INSERT IGNORE INTO vet_specialties
VALUES (3, 3);
INSERT IGNORE INTO vet_specialties
VALUES (4, 2);
INSERT IGNORE INTO vet_specialties
VALUES (5, 1);

INSERT IGNORE INTO types
VALUES (1, 'cat');
INSERT IGNORE INTO types
VALUES (2, 'dog');
INSERT IGNORE INTO types
VALUES (3, 'lizard');
INSERT IGNORE INTO types
VALUES (4, 'snake');
INSERT IGNORE INTO types
VALUES (5, 'bird');
INSERT IGNORE INTO types
VALUES (6, 'hamster');

INSERT IGNORE INTO owners VALUES (1, 'images (1)','George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'george.franklin@gamil.com', 'This owner is hard of hearing');
INSERT IGNORE INTO owners VALUES (2, 'images (9)','Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'betty.davis@gmail.com', 'This owner is a bit impatient');
INSERT IGNORE INTO owners VALUES (3, 'images (6)','Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'eduardo.rodriquez@gmail.com', 'This owner does not read his emails');
INSERT IGNORE INTO owners VALUES (4, 'images (4)','Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'harold.davis@gmail.com', 'This owner is not funny');
INSERT IGNORE INTO owners VALUES (5, 'images (12)','Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'peter.mctavish@gmail.com', 'This owner is always a bit late');
INSERT IGNORE INTO owners VALUES (6, 'images (5)','Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'jean.coleman@gmail.com', 'This owner is very kind');
INSERT IGNORE INTO owners VALUES (7, 'images (3)','Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'jeff.black@gmail.com', 'This owner likes pink');
INSERT IGNORE INTO owners VALUES (8, 'images (8)','Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'maria.escobito@gmail.com.com', 'This owner has peanut allergy');
INSERT IGNORE INTO owners VALUES (9, 'images (7)','David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'david.schroeder@gmail.com', 'This owner is new to this clinic');
INSERT IGNORE INTO owners VALUES (10, 'images_default','Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'carlos.estaban@gmail.com', 'This owner hates animals');


INSERT IGNORE INTO pets
VALUES (1, 'Leo', '2000-09-07', 1, 1, 0, 0);
INSERT IGNORE INTO pets
VALUES (2, 'Basil', '2002-08-06', 6, 2, 0, 0);
INSERT IGNORE INTO pets
VALUES (3, 'Rosy', '2001-04-17', 2, 3, 0, 0);
INSERT IGNORE INTO pets
VALUES (4, 'Jewel', '2000-03-07', 2, 3, 0, 0);
INSERT IGNORE INTO pets
VALUES (5, 'Iggy', '2000-11-30', 3, 4, 0, 0);
INSERT IGNORE INTO pets
VALUES (6, 'George', '2000-01-20', 4, 5, 0, 0);
INSERT IGNORE INTO pets
VALUES (7, 'Samantha', '1995-09-04', 1, 6, 0, 0);
INSERT IGNORE INTO pets
VALUES (8, 'Max', '1995-09-04', 1, 6, 0, 0);
INSERT IGNORE INTO pets
VALUES (9, 'Lucky', '1999-08-06', 5, 7, 0, 0);
INSERT IGNORE INTO pets
VALUES (10, 'Mulligan', '1997-02-24', 2, 8, 0, 0);
INSERT IGNORE INTO pets
VALUES (11, 'Freddy', '2000-03-09', 5, 9, 0, 0);
INSERT IGNORE INTO pets
VALUES (12, 'Lucky', '2000-06-24', 2, 10, 0, 0);
INSERT IGNORE INTO pets
VALUES (13, 'Sly', '2002-06-08', 1, 10, 0, 0);

INSERT IGNORE INTO visits
VALUES (1, 7, '2021-01-01', 'rabies shot');
INSERT IGNORE INTO visits
VALUES (2, 8, '2020-01-02', 'rabies shot');
INSERT IGNORE INTO visits
VALUES (3, 8, '2021-01-03', 'neutered');
INSERT IGNORE INTO visits
VALUES (4, 7, '2021-01-04', 'spayed');
INSERT IGNORE INTO visits
VALUES (5, 13, '2021-09-04', 'spayed');
INSERT IGNORE INTO visits
VALUES (6, 12, '2021-09-04', 'rabies shot');

INSERT INTO schedules
VALUES (1, 'Sunday');
INSERT INTO schedules
VALUES (2, 'Monday');
INSERT INTO schedules
VALUES (3, 'Tuesday');
INSERT INTO schedules
VALUES (4, 'Wednesday');
INSERT INTO schedules
VALUES (5, 'Thursday');
INSERT INTO schedules
VALUES (6, 'Friday');
INSERT INTO schedules
VALUES (7, 'Saturday');



INSERT INTO vet_schedule
VALUES (1, 1);
INSERT INTO vet_schedule
VALUES (1, 2);
INSERT INTO vet_schedule
VALUES (1, 3);
INSERT INTO vet_schedule
VALUES (1, 4);
INSERT INTO vet_schedule
VALUES (1, 5);

INSERT INTO vet_schedule
VALUES (2, 1);
INSERT INTO vet_schedule
VALUES (2, 3);
INSERT INTO vet_schedule
VALUES (2, 5);


INSERT INTO vet_schedule
VALUES (3, 6);
INSERT INTO vet_schedule
VALUES (3, 7);


INSERT INTO vet_schedule
VALUES (6, 1);
INSERT INTO vet_schedule
VALUES (6, 4);
INSERT INTO vet_schedule
VALUES (6, 6);

INSERT INTO ratings
VALUES(1,1,'Johny',5);


