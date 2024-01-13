REPLACE INTO Customers (fname, lname, email, phone) VALUES ('John', 'Doe', 'john.doe@email.com', 123456789);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Emma', 'Taylor', 'emma.taylor@email.com', 876543210);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Michael', 'Clark', 'michael.clark@email.com', 333333333);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Sophia', 'Miller', 'sophia.miller@email.com', 444444444);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Daniel', 'Wilson', 'daniel.wilson@email.com', 555555555);

REPLACE INTO Trip (stationA, stationD, date, quota, idTrain, prix) VALUES ('Marseille', 'Nice', '2024-02-10', 1, '78901', 29.99);
REPLACE INTO Trip (stationA, stationD, date, quota, idTrain, prix) VALUES ('Berlin', 'Munich', '2024-04-05', 2, '65432', 59.99);
REPLACE INTO Trip (stationA, stationD, date, quota, idTrain, prix) VALUES ('Madrid', 'Barcelona', '2024-03-20', 3, '98765', 49.99);
REPLACE INTO Trip (stationA, stationD, date, quota, idTrain, prix) VALUES ('Tokyo', 'Osaka', '2024-05-15', 4, '12345', 89.99);
REPLACE INTO Trip (stationA, stationD, date, quota, idTrain, prix) VALUES ('New York', 'Chicago', '2024-02-28', 5, '56789', 69.99);

REPLACE INTO Tickets (idTicket, idTrip, idCustomers, fname, lname, email, phone, prix, idTrain) VALUES (2, 3, 4, 'John', 'Doe', 'john.doe@email.com', 123456789, 79.99, 2001);
REPLACE INTO Tickets (idTicket, idTrip, idCustomers, fname, lname, email, phone, prix, idTrain) VALUES (3, 1, 2, 'Emma', 'Taylor', 'emma.taylor@email.com', 876543210, 49.99, 2568);
REPLACE INTO Tickets (idTicket, idTrip, idCustomers, fname, lname, email, phone, prix, idTrain) VALUES (4, 5, 3, 'Michael', 'Clark', 'michael.clark@email.com', 333333333, 59.99, 56984);
REPLACE INTO Tickets (idTicket, idTrip, idCustomers, fname, lname, email, phone, prix, idTrain) VALUES (5, 2, 1, 'Sophia', 'Miller', 'sophia.miller@email.com', 444444444, 39.99, 4558);
REPLACE INTO Tickets (idTicket, idTrip, idCustomers, fname, lname, email, phone, prix, idTrain) VALUES (6, 4, 5, 'Daniel', 'Wilson', 'daniel.wilson@email.com', 555555555, 69.99, 1458);