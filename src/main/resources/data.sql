INSERT INTO client(id, name, phone_number) VALUES (1, 'titi', '0676876543');
INSERT INTO client(id, name, phone_number) VALUES (2, 'tonton', '0676876543');
INSERT INTO client(id, name, phone_number) VALUES (3, 'ttmtm', '0676876543');
INSERT INTO client(id, name, phone_number) VALUES (4, 'oioio', '0676876543');

INSERT INTO renter(id, appartments, name, password, admin) VALUES (1, '209', 'florian', 'florian', true);
INSERT INTO renter(id, appartments, name, password, admin) VALUES (2, '126', 'guigui', 'guigui', false);
INSERT INTO renter(id, appartments, name, password, admin) VALUES (3, '203', 'greg', 'greg', false);
INSERT INTO renter(id, appartments, name, password, admin) VALUES (4, '125;208;210', 'jules', 'jules', true);

INSERT INTO period(id, start_date, end_date) VALUES (1, '2019-07-01', '2019-08-01');
INSERT INTO period(id, start_date, end_date) VALUES (2, '2019-09-01', '2019-10-01');
INSERT INTO period(id, start_date, end_date) VALUES (3, '2019-07-09', '2019-08-10');
INSERT INTO period(id, start_date, end_date) VALUES (4, '2019-07-25', '2019-08-30');

INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (1, true, true, '209', 'blabla', 4, 404.32, 'AIRBNB', 1, 1, 1);
INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (2, true, true, '125','blabla', 3, 400.32, 'AIRBNB', 2, 2, 2);
INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (3, true, false, '208','blabla', 1, 600, 'AIRBNB', 3, 3, 3);
INSERT INTO rent(id, cleaning, parking, appartment, comments, nb_client, price, site, client_id, period_id, renter_id) VALUES (4, true, true, '209','blabla', 4, 404.72, 'AIRBNB', 4, 4, 4);

INSERT INTO modification(renter_id, message, date) VALUES (1, "Initial", "2019-08-15 11:44:13");



