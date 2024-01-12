REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Alice', 'Smith', 'alice.smith@email.com', 987654321);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Bob', 'Johnson', 'bob.johnson@email.com', 555555555);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('Eva', 'Williams', 'eva.williams@email.com', 111111111);
REPLACE INTO Customers (fname, lname, email, phone) VALUES ('David', 'Brown', 'david.brown@email.com', 222222222);
REPLACE INTO Trip (stationA, stationD, date, quota) VALUES ('Station A', 'Station D', '2024-01-15', 100);

REPLACE INTO Tickets (idTrip, idCustomer) VALUES (1, 1);
