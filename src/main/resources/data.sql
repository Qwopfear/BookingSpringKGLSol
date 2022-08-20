CREATE  TABLE Client (
     id long auto_increment primary key ,
     name varchar(30)
);

CREATE  TABLE Rentable (
    id long auto_increment primary key ,
    name varchar(30),
    description varchar(225),
    price long,
    area long
);


CREATE  TABLE Reservation (
    id long auto_increment,
    client_id long,
    rent_id long,
    rent_Start DATE,
    rent_End DATE
);
ALTER TABLE Reservation
    ADD FOREIGN KEY (client_id)
        REFERENCES Client(id);

ALTER TABLE Reservation
    ADD FOREIGN KEY (rent_id)
        REFERENCES Rentable(id);



INSERT INTO Rentable (name, description, price, area)VALUES
('Penthouse','Butifull appartment whith good atractions',1000,100),
('House around lake','Natural atraction around wild lake',1000,100);


INSERT INTO Client (name)
VALUES ('SEVA'), ('Diana');


INSERT INTO Reservation (client_id, rent_id,rent_Start,rent_End)VALUES
                                                                  ( 1,1,'2022-12-12','2023-01-01' )

