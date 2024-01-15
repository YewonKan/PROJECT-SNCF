DROP TABLE IF EXISTS DelayNotification;


CREATE TABLE DelayNotification (
                                   idTrain INT AUTO_INCREMENT PRIMARY KEY,
                                   idTrajet INT NOT NULL,
                                   delayDuration INT NOT NULL,
                                   reason VARCHAR(255) NOT NULL,
                                   creationTime TIMESTAMP NOT NULL
);


INSERT INTO DelayNotification (idTrain, idTrajet, delayDuration, reason, creationTime) VALUES
                                                                                           (1, 101, 10, 'Late arrival', '2022-01-01 12:00:00'),
                                                                                           (2, 102, 15, 'Technical issue', '2022-01-02 14:30:00');
