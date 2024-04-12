CREATE TABLE IF NOT EXISTS digits (
    id SERIAL PRIMARY KEY,
    start Int,
    ended Int
);

CREATE TABLE IF NOT EXISTS letters (
    id SERIAL PRIMARY KEY,
    start VARCHAR(1),
    ended VARCHAR(1)
);