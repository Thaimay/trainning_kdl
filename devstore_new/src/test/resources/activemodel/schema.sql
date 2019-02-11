CREATE TABLE blog (
    id IDENTITY,
    title TEXT NOT NULL,
    content TEXT,
    finished BOOLEAN NOT NULL,
    date DATE NOT NULL
);
