CREATE TABLE IF NOT EXISTS delay_information (
                                                 idNotification INT AUTO_INCREMENT PRIMARY KEY,
                                                 idTicket INT NOT NULL,
                                                 delayedMinutes INT NOT NULL,
                                                 delayMotivation VARCHAR(100) NOT NULL,
                                                 refundStatus BOOLEAN NOT NULL
);

INSERT INTO delay_information (idTicket, delayedMinutes, delayMotivation, refundStatus) VALUES
                                                                                            (1, 30, 'Traffic', true),
                                                                                            (2, 45, 'Technical issue', false),
                                                                                            (3, 60, 'Weather conditions', true),
                                                                                            (4, 75, 'Staff shortage', false),
                                                                                            (5, 50, 'Unexpected event', true);
