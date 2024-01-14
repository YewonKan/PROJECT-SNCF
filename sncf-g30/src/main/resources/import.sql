
CREATE TABLE IF NOT EXISTS delay_information (
    idNotification INT AUTO_INCREMENT PRIMARY KEY,
    idTicket INT NOT NULL,
    delayedMinutes INT NOT NULL,
    delayMotivation VARCHAR(100) NOT NULL,
    refundStatus BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS compensation (
    ticketId INT AUTO_INCREMENT PRIMARY KEY,
    client INT NOT NULL,
    type VARCHAR(255) NOT NULL,
    detail VARCHAR(255) NOT NULL,
    validateDate TIMESTAMP NOT NULL,
    amount DOUBLE NOT NULL,
    statusRefund ENUM('NOT_ELIGIBLE', 'REFUNDED', 'PENDING', 'ELIGIBLE') NOT NULL,
    requestDate DATE NOT NULL
);

/*INSERT INTO delay_information (idTicket, delayedMinutes, delayMotivation, refundStatus) VALUES
                                                                                            (1, 30, 'Traffic', true),
                                                                                            (2, 45, 'Technical issue', false),
                                                                                            (3, 60, 'Weather conditions', true),
                                                                                            (4, 75, 'Staff shortage', false),
                                                                                            (5, 50, 'Unexpected event', true);
*/

CREATE TABLE IF NOT EXISTS delayMotivation (
   id INT AUTO_INCREMENT PRIMARY KEY,
   delayMotivation VARCHAR(100) NOT NULL,
    eligibility BOOLEAN NOT NULL
);

INSERT INTO delayMotivation (delayMotivation, eligibility) VALUES
                                                               ('Technical issues', true),     -- Eligible
                                                               ('Weather conditions', false),   -- Eligible
                                                               ('Human error', true),         -- Not Eligible
                                                               ('Maintenance work', true),     -- Eligible
                                                               ('Unexpected events', false);   -- Not Eligible


CREATE TABLE IF NOT EXISTS ticket_information (
                                                  ticketId INT PRIMARY KEY,
                                                  trainId INT NOT NULL,
                                                  trajetId INT NOT NULL,
                                                  prix DOUBLE NOT NULL,
                                                  clientId INT NOT NULL,
                                                  email VARCHAR(255) NOT NULL,
                                                  fname VARCHAR(255) NOT NULL,
                                                  lname VARCHAR(255) NOT NULL,
                                                  phone VARCHAR(20) NOT NULL
);

REPLACE INTO ticket_information (ticketId, trainId, trajetId, prix, clientId, email, fname, lname, phone)
VALUES (1, 123, 456, 89.99, 789, 'example@email.com', 'John', 'Doe', '1234567890');
