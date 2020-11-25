DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vet_schedule IF EXISTS;
DROP TABLE schedules IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE ratings IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;


CREATE TABLE vets
(
    id         INTEGER IDENTITY PRIMARY KEY,
    first_name VARCHAR(30),
    last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners
(
    id         INTEGER IDENTITY PRIMARY KEY,
    profile_picture VARCHAR(255),
    first_name VARCHAR(30),
    last_name  VARCHAR_IGNORECASE(30),
    address    VARCHAR(255),
    city       VARCHAR(80),
    state      char(2),
    telephone  VARCHAR(20),
    email      VARCHAR(30),
    comment    VARCHAR(255)
);

CREATE INDEX owners_last_name ON owners (last_name);
CREATE INDEX owners_id ON owners (id);
CREATE INDEX owners_first_name ON owners (first_name);
CREATE INDEX owners_address ON owners (address);
CREATE INDEX owners_city ON owners (city);
CREATE INDEX owners_telephone ON owners (telephone);
CREATE INDEX owners_email ON owners (email);

CREATE TABLE pets
(
    id         INTEGER IDENTITY PRIMARY KEY,
    name       VARCHAR(30),
    birth_date DATE,
    image_url  VARCHAR(255),
    totalRating INTEGER,
    timesRated INTEGER,
    type_id    INTEGER NOT NULL,
    owner_id   INTEGER NOT NULL
);
ALTER TABLE pets
    ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets
    ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits
(
    id          INTEGER IDENTITY PRIMARY KEY,
    pet_id      INTEGER NOT NULL,
    visit_date  DATE,
    description VARCHAR(255)
);
ALTER TABLE visits
    ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);


/*Code added by Maria Carolina Avila for the APPT team*/
-- CREATE TABLE vet_schedule
-- (
--     schedule_id   INTEGER IDENTITY PRIMARY KEY,
--     vet_id        INTEGER       NOT NULL,
--     room_id       VARCHAR(5)    NOT NULL,
--     day_available NUMERIC(1, 0) NOT NULL
-- );
-- ALTER TABLE vet_schedule
--     ADD CONSTRAINT fk_schedule_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
-- ALTER TABLE vet_schedule
--     ADD CONSTRAINT day_availability_range CHECK (day_available BETWEEN 0 AND 6);


CREATE TABLE schedules
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(80)
);
CREATE INDEX schedules_name ON specialties (name);

CREATE TABLE vet_schedule
(
    vet_id INTEGER NOT NULL,
    day_id INTEGER NOT NULL
);
ALTER TABLE vet_schedule ADD CONSTRAINT fk_vet_schedules_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_schedule ADD CONSTRAINT fk_vet_schedule_days FOREIGN KEY (day_id) REFERENCES schedules (id);

CREATE TABLE ratings
(
    id          INTEGER IDENTITY PRIMARY KEY,
    pet_id      INTEGER NOT NULL,
    username   VARCHAR(30),
    rating      INTEGER
);
ALTER TABLE ratings
    ADD CONSTRAINT fk_ratings_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX ratings_pet_id ON ratings (pet_id);
