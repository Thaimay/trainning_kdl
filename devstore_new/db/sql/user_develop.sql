CREATE USER develop WITH PASSWORD '1234' ;
GRANT ALL ON DATABASE store_development_dev TO develop;
ALTER ROLE develop CREATEDB;
