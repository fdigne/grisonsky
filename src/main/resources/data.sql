INSERT INTO client(id, first_name, name, phone_number) VALUES (1, 'toto', 'titi', '0676876543');
INSERT INTO client(id, first_name, name, phone_number) VALUES (2, 'tutu', 'tonton', '0676876543');
INSERT INTO client(id, first_name, name, phone_number) VALUES (3, 'eeee', 'ttmtm', '0676876543');
INSERT INTO client(id, first_name, name, phone_number) VALUES (4, 'pppp', 'oioio', '0676876543');

INSERT INTO renter(id, appartments, name) VALUES (1, '209', 'florian');
INSERT INTO renter(id, appartments, name) VALUES (2, '126', 'guigui');
INSERT INTO renter(id, appartments, name) VALUES (3, '203', 'greg');
INSERT INTO renter(id, appartments, name) VALUES (4, '125;208;210', 'jules');

INSERT INTO period(id, start_date, end_date) VALUES (1, '2019-07-01', '2019-08-01');
INSERT INTO period(id, start_date, end_date) VALUES (2, '2019-09-01', '2019-10-01');
INSERT INTO period(id, start_date, end_date) VALUES (3, '2019-07-09', '2019-08-10');
INSERT INTO period(id, start_date, end_date) VALUES (4, '2019-07-25', '2019-08-30');

INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (1, true, true, '209', 'blabla', 4, 404.32, 'AIRBNB', 1, 1, 1);
INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (2, true, true, '125','blabla', 3, 400.32, 'AIRBNB', 2, 2, 2);
INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (3, true, false, '208','blabla', 1, 600, 'AIRBNB', 3, 3, 3);
INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (4, true, true, '209','blabla', 4, 404.72, 'AIRBNB', 4, 4, 4);





