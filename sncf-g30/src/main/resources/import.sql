INSERT INTO delay_information (idTrajet, idTrain, delayedMinutes, delayMotivation, delayedDate)
VALUES
    (1, 123, 30, 'Motivation 1', '2024-01-13 10:15:00'),
    (2, 234, 45, 'Motivation 2', '2024-01-13 11:30:00'),
    (3, 345, 15, 'Motivation 3', '2024-01-13 14:45:00'),
    (4, 456, 60, 'Motivation 4', '2024-01-13 16:00:00'),
    (5, 567, 20, 'Motivation 5', '2024-01-13 18:15:00');



REPLACE INTO delay_information (idTrajet, idTrain, delayedMinutes, delayMotivation, delayedDate)
VALUES
    (1, 123, 30, 'Motivation 1', '2024-01-13 10:15:00'),
    (2, 234, 45, 'Motivation 2', '2024-01-13 11:30:00'),
    (3, 345, 15, 'Motivation 3', '2024-01-13 14:45:00'),
    (4, 456, 60, 'Motivation 4', '2024-01-13 16:00:00'),
    (5, 567, 20, 'Motivation 5', '2024-01-13 18:15:00');



REPLACE INTO delay_motivation (delayMotivation, eligibility) VALUES
                                                               ('Motivation 1', true),     -- Eligible
                                                               ('Motivation 2', false),    -- Eligible
                                                               ('Motivation 3', true),     -- Not Eligible
                                                               ('Motivation 4', true),     -- Eligible
                                                               ('Motivation 5', false);