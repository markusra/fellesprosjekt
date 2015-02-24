INSERT INTO BRUKER (brukernavn, passord, fornavn, etternavn, epost)
VALUES ('markusra', 'test', 'Markus', 'Rauhut', 'markus.rauhut@gmail.com');

INSERT INTO BRUKER (brukernavn, passord, fornavn, etternavn, epost)
VALUES ('vebjorbe', 'test2', 'Vebjørn', 'Berg', 'vebbi.berg@gmail.com');

INSERT INTO AVTALE (start, slutt, navn, beskrivelse, sted, adminID)
VALUES ('25.01.2015 10:15', '25.01.2015 12:00', 'Møte med PU', 'Jaja', 'R41', 1);

INSERT INTO GRUPPE (navn, adminID)
VALUES ('Gruppe 35', 2);

INSERT INTO GRUPPEBRUKER (gruppeID, brukerID)
VALUES (1, 3);

INSERT INTO AVTALEBRUKER (deltar, avtaleID, brukerID)
VALUES (null, 5, 3);

INSERT INTO MOTEROM (sted, størrelse, start, slutt, navn)
VALUES ('R41', 15, '25.01.2015 10:15', '25.01.2015 12:00', 'Berzelius')