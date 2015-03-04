INSERT INTO BRUKER (brukernavn, passord, fornavn, etternavn, epost)
VALUES ('markusra', 'test', 'Markus', 'Rauhut', 'markus.rauhut@gmail.com');

INSERT INTO BRUKER (brukernavn, passord, fornavn, etternavn, epost)
VALUES ('vebjorbe', 'test2', 'Vebjorn', 'Berg', 'vebbi.berg@gmail.com');

INSERT INTO GRUPPE (navn, supergruppeID)
VALUES ('Gruppe 35', 1);

INSERT INTO GRUPPEBRUKER (gruppeID, brukerID)
VALUES (1, 1);

INSERT INTO AVTALEBRUKER (deltar, avtaleID, brukerID, admin)
VALUES (null, 1, 1, True);

INSERT INTO MOTEROM (sted, navn, storrelse)
VALUES ('R41', 'Berzelius', 15);

INSERT INTO AVTALE (start, slutt, navn, beskrivelse, sted, moteromID)
VALUES (250120151000, 250120151100, 'Møte med PU', 'Jaja', 'R41', 1);