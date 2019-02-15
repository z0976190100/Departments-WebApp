CREATE TABLE department
(
  title     TEXT,
  id        SERIAL NOT NULL
    CONSTRAINT department_pkey
    PRIMARY KEY
);

CREATE TABLE employee2
(
  id                 SERIAL NOT NULL
    CONSTRAINT employee2_pkey
    PRIMARY KEY,
  first_name         TEXT,
  last_name          TEXT,
  department_id_long BIGINT
    CONSTRAINT employee2_department_id_long_fkey
    REFERENCES department,
  login              TEXT,
  pass               TEXT,
  birth_date         TEXT
);


