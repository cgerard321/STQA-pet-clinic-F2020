INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'images (1)','George', 'Franklin', '110 W. Liberty St.', 'Madison', 'WI', '6085551023', 'george.franklin@gamil.com', 'This owner is hard of hearing');
INSERT INTO owners VALUES (2, 'images (9)','Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', 'WI', '6085551749', 'betty.davis@gmail.com', 'This owner is a bit impatient');
INSERT INTO owners VALUES (3, 'images (6)','Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', 'NY', '6085558763', 'eduardo.rodriquez@gmail.com', 'This owner does not read his emails');
INSERT INTO owners VALUES (4, 'images (4)','Harold', 'Davis', '563 Friendly St.', 'Windsor', 'WI', '6085553198', 'harold.davis@gmail.com', 'This owner is not funny');
INSERT INTO owners VALUES (5, 'images (12)','Peter', 'McTavish', '2387 S. Fair Way', 'Madison', 'NY', '6085552765', 'peter.mctavish@gmail.com', 'This owner is always a bit late');
INSERT INTO owners VALUES (6, 'images (5)','Jean', 'Coleman', '105 N. Lake St.', 'Monona', 'CA', '6085552654', 'jean.coleman@gmail.com', 'This owner is very kind');
INSERT INTO owners VALUES (7, 'images (3)','Jeff', 'Black', '1450 Oak Blvd.', 'Monona', 'CA', '6085555387', 'jeff.black@gmail.com', 'This owner likes pink');
INSERT INTO owners VALUES (8, 'images (8)','Maria', 'Escobito', '345 Maple St.', 'Madison', 'WI', '6085557683', 'maria.escobito@gmail.com.com', 'This owner has peanut allergy');
INSERT INTO owners VALUES (9, 'images (7)','David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', 'WI', '6085559435', 'david.schroeder@gmail.com', 'This owner is new to this clinic');
INSERT INTO owners VALUES (10, 'images_default','Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', 'AK', '6085555487', 'carlos.estaban@gmail.com', 'This owner hates animals');

INSERT INTO pets VALUES (1, 'Leo', '2010-09-07', 'https://i.pinimg.com/736x/33/32/6d/33326dcddbf15c56d631e374b62338dc.jpg', 0, 0, 1, 1);
INSERT INTO pets VALUES (2, 'Basil', '2012-08-06', 'https://i.ytimg.com/vi/1BF1kfwAVN4/maxresdefault.jpg', 5, 1, 6, 2);
INSERT INTO pets VALUES (3, 'Rosy', '2011-04-17', 'https://www.thesprucepets.com/thmb/sfuyyLvyUx636_Oq3Fw5_mt-PIc=/3760x2820/smart/filters:no_upscale()/adorable-white-pomeranian-puppy-spitz-921029690-5c8be25d46e0fb000172effe.jpg', 0, 0, 2, 3);
INSERT INTO pets VALUES (4, 'Jewel', '2010-03-07', 'https://www.rd.com/wp-content/uploads/2019/01/shutterstock_1176034804.jpg', 0, 0, 2, 3);
INSERT INTO pets VALUES (5, 'Iggy', '2010-11-30', 'https://cf.ltkcdn.net/small-pets/images/std/261974-425x274-lizard-pet-cute.jpg', 10, 1, 3, 4);
INSERT INTO pets VALUES (6, 'George', '2010-01-20', 'https://imgur.com/tDrCeLB', 0, 0, 4, 5);
INSERT INTO pets VALUES (7, 'Samantha', '2012-09-04', 'https://i.pinimg.com/originals/0c/1c/a1/0c1ca1955e2b0c5469ba17da2b1b9b96.jpg', 0, 0, 1, 6);
INSERT INTO pets VALUES (8, 'Max', '2012-09-04', 'https://d17fnq9dkz9hgj.cloudfront.net/uploads/2018/03/Scottish-Fold_01.jpg', 0, 0, 1, 6);
INSERT INTO pets VALUES (9, 'Lucky', '2011-08-06', 'https://pm1.narvii.com/7095/6ef53a0241f17779df40f265a335e1c26ec5b4c2r1-972-1196v2_hq.jpg', 8, 1, 5, 7);
INSERT INTO pets VALUES (10, 'Mulligan', '2007-02-24', 'https://cdn5.littlethings.com/app/uploads/2017/05/cute-dog-names-1200.jpg', 0, 0, 2, 8);
INSERT INTO pets VALUES (11, 'Freddy', '2010-03-09', 'https://i.ytimg.com/vi/MjEaniGTrx0/maxresdefault.jpg', 20, 2, 5, 9);
INSERT INTO pets VALUES (12, 'Lucky', '2010-06-24', 'https://i.pinimg.com/originals/5c/d7/47/5cd7478e9a6f8893b288512b6181d658.jpg', 0, 0, 2, 10);
INSERT INTO pets VALUES (13, 'Sly', '2012-06-08', 'https://i.ytimg.com/vi/SQJrYw1QvSQ/maxresdefault.jpg', 0, 0, 1, 10);


INSERT INTO visits VALUES (1, 7, '2021-01-01', 'rabies shot');
INSERT INTO visits VALUES (2, 8, '2020-01-02', 'rabies shot');
INSERT INTO visits VALUES (3, 8, '2021-01-03', 'neutered');
INSERT INTO visits VALUES (4, 7, '2021-01-04', 'spayed');
INSERT INTO visits VALUES (5, 13, '2021-09-04', 'spayed');
INSERT INTO visits VALUES (6, 12, '2021-09-04', 'rabies shot');
INSERT INTO visits VALUES (7, 6, '2020-12-23', 'rabies shot');
INSERT INTO visits VALUES (8, 11, '2020-12-17', 'rabies shot');
INSERT INTO visits VALUES (9, 10, '2020-12-07', 'neutered');
INSERT INTO visits VALUES (10, 9, '2020-12-11', 'spayed');
INSERT INTO visits VALUES (11, 3, '2020-12-29', 'spayed');
INSERT INTO visits VALUES (12, 4, '2020-11-30', 'rabies shot');


INSERT INTO schedules VALUES (1, 'Monday');
INSERT INTO schedules VALUES (2, 'Tuesday');
INSERT INTO schedules VALUES (3, 'Wednesday');
INSERT INTO schedules VALUES (4, 'Thursday');
INSERT INTO schedules VALUES (5, 'Friday');
INSERT INTO schedules VALUES (6, 'Saturday');
INSERT INTO schedules VALUES (7, 'Sunday');


INSERT INTO vet_schedule VALUES (1, 1);
INSERT INTO vet_schedule VALUES (1, 2);
INSERT INTO vet_schedule VALUES (1, 3);
INSERT INTO vet_schedule VALUES (1, 4);
INSERT INTO vet_schedule VALUES (1, 5);

INSERT INTO vet_schedule VALUES (2, 1);
INSERT INTO vet_schedule VALUES (2, 3);
INSERT INTO vet_schedule VALUES (2, 5);

INSERT INTO vet_schedule VALUES (3, 6);
INSERT INTO vet_schedule VALUES (3, 7);

INSERT INTO vet_schedule VALUES (6, 1);
INSERT INTO vet_schedule VALUES (6, 4);
INSERT INTO vet_schedule VALUES (6, 6);

INSERT INTO ratings VALUES(1,1,'Johny',5);


INSERT INTO event VALUES (5, '2020-12-12','Open house', '10h00am');
INSERT INTO event VALUES (2, '2020-12-25', 'Christmas!', '');
