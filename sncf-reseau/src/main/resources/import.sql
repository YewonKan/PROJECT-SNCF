DROP TABLE IF EXISTS DelayNotification;




INSERT INTO DelayNotification (idTrain, idTrajet, delayDuration, reason, creationTime) VALUES
                                                                                           (1, 101, 10, 'Late arrival', '2022-01-01 12:00:00'),
                                                                                           (2, 102, 15, 'Technical issue', '2022-01-02 14:30:00');
