CREATE TABLE IF NOT EXISTS decimal_intervals (
    id SERIAL PRIMARY KEY,
    start Int,
    ended Int
);

CREATE TABLE IF NOT EXISTS string_intervals (
    id SERIAL PRIMARY KEY,
    start VARCHAR(1),
    ended VARCHAR(1)
);