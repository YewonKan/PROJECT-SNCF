INSERT INTO delay_information (idTrajet, idTrain, delayedMinutes, delayMotivation, delayedDate)
VALUES
    (1, 123, 30, 'Motivation 1', '2024-01-13 10:15:00'),
    (2, 234, 45, 'Motivation 2', '2024-01-13 11:30:00'),
    (3, 345, 15, 'Motivation 3', '2024-01-13 14:45:00'),
    (4, 456, 60, 'Motivation 4', '2024-01-13 16:00:00'),
    (5, 567, 20, 'Motivation 5', '2024-01-13 18:15:00');
CREATE TABLE IF NOT EXISTS ticket_information (
                                                  ticketId INT PRIMARY KEY AUTO_INCREMENT,
                                                  trainId INT NOT NULL,
                                                  trajetId INT NOT NULL,
                                                  prix DOUBLE NOT NULL,
                                                  clientId INT NOT NULL
);
INSERT INTO ticket_information (ticketId, trainId, trajetId, prix, clientId)
VALUES
    (1, 2, 1, 78.50, 1),
    (2, 2, 2, 102.75, 1),
    (3, 2, 3, 45.25, 1),
    (4, 2, 4, 65.00, 1),
    (5, 2, 5, 98.20, 1),
    (6, 2, 6, 72.80, 1),
    (7, 2, 7, 55.75, 1),
    (8, 2, 8, 120.50, 1),
    (9, 2, 9, 35.25, 1),
    (10, 2, 10, 88.90, 1);

CREATE TABLE IF NOT EXISTS delay_information (
                                                 idNotification INT PRIMARY KEY AUTO_INCREMENT,
                                                 idTrajet INT NOT NULL,
                                                 idTrain INT NOT NULL,
                                                 delayedMinutes INT NOT NULL,
                                                 delayMotivation VARCHAR(100) NOT NULL,
    delayedDate TIMESTAMP NOT NULL
    );

INSERT INTO delay_information (idTrajet, idTrain, delayedMinutes, delayMotivation, delayedDate)
VALUES
    (1, 123, 30, 'Motivation 1', '2024-01-13 10:15:00'),
    (2, 234, 45, 'Motivation 2', '2024-01-13 11:30:00'),
    (3, 345, 15, 'Motivation 3', '2024-01-13 14:45:00'),
    (4, 456, 60, 'Motivation 4', '2024-01-13 16:00:00'),
    (5, 567, 20, 'Motivation 5', '2024-01-13 18:15:00');

CREATE TABLE IF NOT EXISTS delayMotivation (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               delayMotivation VARCHAR(100) NOT NULL,
    eligibility BOOLEAN NOT NULL
    );

INSERT INTO delayMotivation (delayMotivation, eligibility) VALUES
                                                               ('Motivation 1', true),     -- Eligible
                                                               ('Motivation 2', false),    -- Eligible
                                                               ('Motivation 3', true),     -- Not Eligible
                                                               ('Motivation 4', true),     -- Eligible
                                                               ('Motivation 5', false);