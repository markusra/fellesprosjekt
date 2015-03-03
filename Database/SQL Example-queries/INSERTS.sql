INSERT INTO BRUKER (brukernavn, passord, fornavn, etternavn, epost)
VALUES ('markusra', 'test', 'Markus', 'Rauhut', 'markus.rauhut@gmail.com');

INSERT INTO BRUKER (brukernavn, passord, fornavn, etternavn, epost)
VALUES ('vebjorbe', 'test2', 'Vebjorn', 'Berg', 'vebbi.berg@gmail.com');

INSERT INTO AVTALE (start, slutt, navn, beskrivelse, sted)
VALUES ('25.01.2015 10:15', '25.01.2015 12:00', 'Møte med PU', 'Jaja', 'R41');

INSERT INTO GRUPPE (navn, supergruppeID)
VALUES ('Gruppe 35', 1);

INSERT INTO GRUPPEBRUKER (gruppeID, brukerID)
VALUES (1, 1);

INSERT INTO AVTALEBRUKER (deltar, avtaleID, brukerID, admin)
VALUES (null, 1, 1, True);

INSERT INTO MOTEROM (sted, storrelse, start, slutt, navn)
VALUES ('R41', 15, '25.01.2015 10:15', '25.01.2015 12:00', 'Berzelius')