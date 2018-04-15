CREATE TABLE department
(
  title     TEXT,
  employees BIGINT,
  id        SERIAL NOT NULL
    CONSTRAINT department_pkey
    PRIMARY KEY
);

CREATE TABLE employee
(
  id                 SERIAL NOT NULL
    CONSTRAINT employee_pkey
    PRIMARY KEY,
  first_name         TEXT,
  last_name          TEXT,
  department_id_long BIGINT,
  login              TEXT,
  pass               TEXT,
  birth_date         TEXT
);


-- TODO: dep id -> foreign key