CREATE TABLE IF NOT EXISTS PERSONS (
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    city_of_living VARCHAR(255),
    PRIMARY KEY (name, surname, age)
);