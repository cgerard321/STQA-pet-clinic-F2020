INSERT INTO vets VALUES (1, 'James', 'Carter') ON CONFLICT DO NOTHING;
INSERT INTO vets VALUES (2, 'Helen', 'Leary') ON CONFLICT DO NOTHING;
INSERT INTO vets VALUES (3, 'Linda', 'Douglas') ON CONFLICT DO NOTHING;
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega') ON CONFLICT DO NOTHING;
INSERT INTO vets VALUES (5, 'Henry', 'Stevens') ON CONFLICT DO NOTHING;
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins') ON CONFLICT DO NOTHING;

INSERT INTO specialties VALUES (1, 'radiology') ON CONFLICT DO NOTHING;
INSERT INTO specialties VALUES (2, 'surgery') ON CONFLICT DO NOTHING;
INSERT INTO specialties VALUES (3, 'dentistry') ON CONFLICT DO NOTHING;

INSERT INTO vet_specialties VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (3, 2) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (4, 2) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (5, 1) ON CONFLICT DO NOTHING;

INSERT INTO types VALUES (1, 'cat') ON CONFLICT DO NOTHING;
INSERT INTO types VALUES (2, 'dog') ON CONFLICT DO NOTHING;
INSERT INTO types VALUES (3, 'lizard') ON CONFLICT DO NOTHING;
INSERT INTO types VALUES (4, 'snake') ON CONFLICT DO NOTHING;
INSERT INTO types VALUES (5, 'bird') ON CONFLICT DO NOTHING;
INSERT INTO types VALUES (6, 'hamster') ON CONFLICT DO NOTHING;


INSERT INTO owners VALUES (1, 'images (1)','George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'george.franklin@gamil.com', 'This owner is hard of hearing') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (2, 'images (9)','Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'betty.davis@gmail.com', 'This owner is a bit impatient') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (3, 'images (6)','Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'eduardo.rodriquez@gmail.com', 'This owner does not read his emails') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (4, 'images (4)','Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'harold.davis@gmail.com', 'This owner is not funny') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (5, 'images (12)','Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'peter.mctavish@gmail.com', 'This owner is always a bit late') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (6, 'images (5)','Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'jean.coleman@gmail.com', 'This owner is very kind') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (7, 'images (3)','Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'jeff.black@gmail.com', 'This owner likes pink') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (8, 'images (8)','Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'maria.escobito@gmail.com.com', 'This owner has peanut allergy') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (9, 'images (7)','David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'david.schroeder@gmail.com', 'This owner is new to this clinic') ON CONFLICT DO NOTHING;
INSERT INTO owners VALUES (10, 'images_default','Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'carlos.estaban@gmail.com', 'This owner hates animals') ON CONFLICT DO NOTHING;


INSERT INTO pets VALUES (1, 'Leo', '2000-09-07', 1, 1, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (2, 'Basil', '2002-08-06', 6, 2, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (3, 'Rosy', '2001-04-17', 2, 3, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (4, 'Jewel', '2000-03-07', 2, 3, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (5, 'Iggy', '2000-11-30', 3, 4, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (6, 'George', '2000-01-20', 4, 5, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (7, 'Samantha', '1995-09-04', 1, 6, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (8, 'Max', '1995-09-04', 1, 6, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (9, 'Lucky', '1999-08-06', 5, 7, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (10, 'Mulligan', '1997-02-24', 2, 8, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (11, 'Freddy', '2000-03-09', 5, 9, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (12, 'Lucky', '2000-06-24', 2, 10, 0, 0) ON CONFLICT DO NOTHING;
INSERT INTO pets VALUES (13, 'Sly', '2002-06-08', 1, 10, 0, 0) ON CONFLICT DO NOTHING;

INSERT INTO visits VALUES (1, 7, '2021-01-01', 'rabies shot') ON CONFLICT DO NOTHING;
INSERT INTO visits VALUES (2, 8, '2020-01-02', 'rabies shot') ON CONFLICT DO NOTHING;
INSERT INTO visits VALUES (3, 8, '2021-01-03', 'neutered') ON CONFLICT DO NOTHING;
INSERT INTO visits VALUES (4, 7, '2021-01-04', 'spayed') ON CONFLICT DO NOTHING;
INSERT INTO visits VALUES (5, 13, '2021-09-04', 'spayed') ON CONFLICT DO NOTHING;
INSERT INTO visits VALUES (6, 12, '2021-09-04', 'rabies shot') ON CONFLICT DO NOTHING;

