-- Create tables if not exist




-- Insert or replace data

REPLACE INTO Client (Cname, Cstatus) VALUES
                                         ('Client A', 'grand voyageur'),
                                         ('Client B', 'voyageur simple'),
                                         ('Client C', '');

REPLACE INTO Compensation (client_id, type, details, validity_date) VALUES
                                                                        (1, 'Bon d''achat', 'Présentez ce bon d''achat lors de la prochaine transaction pour bénéficier de la réduction.', '2024-12-31'),
                                                                        (2, 'Remboursement par chèque', 'Envoyé par la poste', NULL),
                                                                        (3, 'Remboursement par chèque', 'Envoyé par la poste', NULL);
