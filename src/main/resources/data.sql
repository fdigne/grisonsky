INSERT INTO bill(id, cleaning, service) VALUES (1, 0, 0);
INSERT INTO bill(id, cleaning, service) VALUES (2, 0, 0);
INSERT INTO bill(id, cleaning, service) VALUES (3, 0, 0);
INSERT INTO bill(id, cleaning, service) VALUES (4, 0, 0);

INSERT INTO renter(id, appartments, name, password, admin, bill_id) VALUES (1, '209', 'florian', 'florian', true, 1);
INSERT INTO renter(id, appartments, name, password, admin, bill_id) VALUES (2, '126', 'guigui', 'guigui', false, 2);
INSERT INTO renter(id, appartments, name, password, admin, bill_id) VALUES (3, '203', 'greg', 'greg', false, 3);
INSERT INTO renter(id, appartments, name, password, admin, bill_id) VALUES (4, '125;208;210', 'jules', 'jules', true, 4);


INSERT INTO modification(renter_id, message, date) VALUES (4, "Initial", "2019-08-15 11:44:13");


