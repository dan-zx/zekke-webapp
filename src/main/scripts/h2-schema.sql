DROP TABLE IF EXISTS path CASCADE;
DROP TABLE IF EXISTS place CASCADE;

CREATE TABLE place (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE path (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    distance DOUBLE NOT NULL,
    from_place_id BIGINT NOT NULL,
    to_place_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE place ADD CONSTRAINT place_position UNIQUE (latitude, longitude);
ALTER TABLE path ADD CONSTRAINT path_uq UNIQUE (from_place_id, to_place_id);
ALTER TABLE path ADD CONSTRAINT path_fk_from_place FOREIGN KEY (from_place_id) REFERENCES place (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE path ADD CONSTRAINT path_fk_to_place FOREIGN KEY (to_place_id) REFERENCES place (id) ON DELETE CASCADE ON UPDATE CASCADE;