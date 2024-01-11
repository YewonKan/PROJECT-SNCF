CREATE TABLE IF NOT EXISTS delay_information (
                                                 idNotification INT PRIMARY KEY AUTO_INCREMENT,
                                                 idTrajet INT NOT NULL,
                                                 idTrain INT NOT NULL,
                                                 delayedMinutes INT NOT NULL,
                                                 delayMotivation VARCHAR(100) NOT NULL,
                                                 delayedDate DATETIME NOT NULL

);

INSERT INTO delay_information (idTrajet, idTrain,delayMotivation, delayedMinutes,delayedDate)
VALUES
    (1, 30, 'Traffic', 30, '2024-01-11 12:00:00'),
    (2, 45, 'Technical issue', 45, '2024-01-11 13:30:00'),
    (3, 60, 'Weather conditions', 60, '2024-01-11 15:00:00'),
    (4, 75, 'Staff shortage', 75, '2024-01-11 16:45:00'),
    (5, 50, 'Unexpected event', 50, '2024-01-11 18:15:00');