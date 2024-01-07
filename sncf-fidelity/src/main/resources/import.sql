-- Create tables if not exist

CREATE TABLE IF NOT EXISTS Artist (
                                      idArtist INT PRIMARY KEY,
                                      name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Customer (
                                        idCustomer INT PRIMARY KEY,
                                        email VARCHAR(255),
                                        fname VARCHAR(255),
                                        lname VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Location (
                                        idLocation INT PRIMARY KEY,
                                        name VARCHAR(255),
                                        standingGauge INT
);

CREATE TABLE IF NOT EXISTS Vendor (
                                      idVendor INT PRIMARY KEY,
                                      name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Venue (
                                     idVenue INT PRIMARY KEY,
                                     venueDate DATE,
                                     idLocation INT,
                                     FOREIGN KEY (idLocation) REFERENCES Location(idLocation)
);

CREATE TABLE IF NOT EXISTS VenueLineUp (
                                           idVenue INT,
                                           showTime TIME,
                                           artist_idArtist INT,
                                           FOREIGN KEY (idVenue) REFERENCES Venue(idVenue),
                                           FOREIGN KEY (artist_idArtist) REFERENCES Artist(idArtist)
);

CREATE TABLE IF NOT EXISTS VenueQuota (
                                          seatingQuota INT,
                                          standingQuota INT,
                                          vendor_idVendor INT,
                                          venue_idVenue INT,
                                          FOREIGN KEY (vendor_idVendor) REFERENCES Vendor(idVendor),
                                          FOREIGN KEY (venue_idVenue) REFERENCES Venue(idVenue)
);

CREATE TABLE IF NOT EXISTS Ticket (
                                      idTicket INT PRIMARY KEY,
                                      seatReference VARCHAR(255),
                                      ticketKey VARCHAR(255),
                                      validUntil DATE,
                                      idCustomer INT,
                                      idVendor INT,
                                      idVenue INT,
                                      FOREIGN KEY (idCustomer) REFERENCES Customer(idCustomer),
                                      FOREIGN KEY (idVendor) REFERENCES Vendor(idVendor),
                                      FOREIGN KEY (idVenue) REFERENCES Venue(idVenue)
);

CREATE TABLE IF NOT EXISTS Client (
                                      id INT PRIMARY KEY,
                                      Cname VARCHAR(255),
                                      Cstatus VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Compensation (
                                            client_id INT,
                                            type VARCHAR(255),
                                            details VARCHAR(255),
                                            validity_date DATE,
                                            FOREIGN KEY (client_id) REFERENCES Client(id)
);

-- Insert or replace data

REPLACE INTO Artist (idArtist, name) VALUES (1, 'Radiohead'), (2, 'Billie Eilish'), (3, 'French Cowboy');

REPLACE INTO Customer (idCustomer, email, fname, lname) VALUES (1, 'nicolas.herbaut@univ-paris1.fr', 'nicolas', 'herbaut');

REPLACE INTO Location (idLocation, name, standingGauge) VALUES (1, 'Bordeaux Akea Arena', 2000), (2, 'Le Moulin (Marseille)', 800), (3, 'Espace 3000 (Hyères)', 200);

REPLACE INTO Vendor (idVendor, name) VALUES (1, 'fnac'), (2, 'ticket master');

REPLACE INTO Venue (idVenue, venueDate, idLocation) VALUES (1, '2022-06-06', 1), (2, '2022-06-06', 1), (3, '2021-02-03', 3), (4, '2022-01-03', 2);

REPLACE INTO VenueLineUp (idVenue, showTime, artist_idArtist) VALUES (1, '20:00', 1), (2, '22:00', 1), (2, '19:30', 2), (3, '22:00', 3);

REPLACE INTO VenueQuota (seatingQuota, standingQuota, vendor_idVendor, venue_idVenue) VALUES (10, 20, 1, 2), (15, 50, 2, 1), (5, 5, 2, 2), (0, 0, 2, 3);

REPLACE INTO Ticket (idTicket, seatReference, ticketKey, validUntil, idCustomer, idVendor, idVenue) VALUES (1, NULL, 'dummyKey', NULL, 1, 1, 1), (2, 'ABC-01', 'dummyKey', NULL, 1, 2, 1);

REPLACE INTO Client (idClient, Cname, Cstatus) VALUES (1, 'Client A', 'grand voyageur'), (2, 'Client B', 'voyageur simple'), (3, 'Client C', '');

REPLACE INTO Compensation (client_id, type, details, validity_date) VALUES
                                                                        (1, 'Bon d''achat', 'Présentez ce bon d''achat lors de la prochaine transaction pour bénéficier de la réduction.', '2024-12-31'),
                                                                        (2, 'Remboursement par chèque', 'Envoyé par la poste', NULL),
                                                                        (3, 'Remboursement par chèque', 'Envoyé par la poste', NULL);
