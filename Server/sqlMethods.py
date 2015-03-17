# SQL-metodene
import MySQLdb
import itertools
import json

debug = False
reply = ''
splitChar = '#'


def interpreter (data):

    raw = data.split(splitChar)
    command = raw[0]
    data = eval(raw[1])

    if (command == 'k2pj39as9d0uo34jkh41'): # LOGIN
        username = data[0]
        password = data[1]

        reply = login(username, password)

    elif (command == 'y2sg59sleuc5el70sjr3'): # CREATE USER
        username = "'" + data[0] + "'"
        password = "'" + data[1] + "'"
        epost = "'" + data[2] + "'"

        if (data[3] == "null"):
            fornavn = "null"
        else:
            fornavn = "'" + data [3] + "'"

        if (data[4] == "null"):
            etternavn = "null"
        else:
            etternavn = "'" + data [4] + "'"

        reply = createUser(username, password, epost, fornavn, etternavn)

    elif (command == 'u4sl29fjanz680slla0p'): # GET ALL USERS
        reply = getAllUsers()

    elif (command == 'o35n2b1hsk1oa901jsnt'): # GET SPECIFIC USER
        userString = data
        reply = getSpecificUser(userString)

    elif (command == 'j2az937ls9mey36z2h0l'): # GET ALL GROUPS
        reply = getAllGroups()

    elif (command == 'h4ks827db5kz791ke2n5'): # GET FILTERED ROOMS
        start = str(data[0])
        slutt = str(data[1])
        storrelse = str(data[2])

        reply = getOccupiedRooms(start, slutt, storrelse)

    elif (command == 'a92mfdy37sk9senc7smw'): # CREATE APPOINTMENT
        navn = "'" + data[0] + "'"
        start = data[1]
        slutt = data[2]

        if (data[3] == "null"):
            beskrivelse = "null"
        else:
            beskrivelse = "'" + data[3] + "'"

        if (data[4] == "null"):
            sted = "null"
        else:
            sted = "'" + data[4] + "'"

        if (data[5] == "null"):
            moteromID = "null"
        else:
            moteromID = data[5]

        reply = createAppointment(navn, start, slutt, beskrivelse, sted, moteromID)

    elif (command == 'g2z8fvj39s3mo899asd3'): # CREATE GROUP
        navn = "'" + data[0] + "'"

        if (data[1] == "null"):
            supergruppeID = "null"
        else:
            supergruppeID = data[1]

        reply = createGroup(navn, supergruppeID)

    elif (command == 'g7sj34nd8xjake4hr8nf'): # GET SPECIFIC GROUP
        navn = "'" + data + "'"
        reply = getSpecificGroup(navn)

    elif (command == 'z9sh38fjka5hj79mvgh1'): # CREATE GROUP MEMBER
        brukerID = str(data[0])
        gruppeID = str(data[1])
        admin = str(data[2])

        reply = createGroupMember(brukerID, gruppeID, admin)

    elif (command == 't37zhenk58slro38za7q'): # CREATE APPOINTMENT MEMBER
        brukerID = str(data[0])
        avtaleID = str(data[1])
        deltar = str(data[2])
        admin = str(data[3])

        reply = createAppointmentMember(brukerID, avtaleID, deltar, admin)

    elif (command == 'f2ks94lfurb68z52k9ah'): # GET APPOINTMENTS
        brukerID = str(data[0])
        start = str(data[1])
        slutt = str(data[2])

        reply = getAppointments(brukerID, start, slutt)

    elif (command == 's8cj2jak602lfun4h6z8'): # GET SPECIFIC APPOINTMENT
        avtaleID = str(data)

        reply = getSpecificAppointment(avtaleID)

    print reply
    return command + splitChar + reply

def validateUser(data):
    if data != []:
        return True
    return False

def login(username, password):
    data = executeSQL("SELECT brukerID, brukernavn, passord, fornavn, etternavn, epost FROM BRUKER WHERE brukernavn ='" \
    + username + "' AND passord ='" + password + "'")
    return data

def createUser(username, password, email, firstname, lastname):
    data = executeSQL("INSERT INTO BRUKER (brukernavn, passord, epost, fornavn, etternavn) VALUES (" \
    + username + ", " + password + ", " + email + ", " + firstname + ", " + lastname + ")")
    return data

def getSpecificUser(streng):
    data = executeSQL("SELECT brukerID, brukernavn, fornavn, etternavn FROM BRUKER " \
    "WHERE brukernavn LIKE '%" + streng + "%' OR fornavn LIKE '%" + streng + "%' OR etternavn LIKE '%" + streng + "%'")
    return data

def getAllUsers():
    data = executeSQL("SELECT brukerID, brukernavn, fornavn, etternavn FROM BRUKER")
    return data

def getOccupiedRooms(start, slutt, storrelse):
    data = executeSQL(  "SELECT moteromID, sted, navn, storrelse " \
                        "FROM MOTEROM " \
                        "WHERE storrelse >= " + storrelse + " AND moteromID NOT IN (" \
                        "SELECT moteromID " \
                        "FROM MOTEROM " \
                        "WHERE moteromID IN (" \
                        "SELECT moteromID " \
                        "FROM AVTALE " \
                        "WHERE (start <= " + start + " AND slutt >= " + start + ") OR " \
                        "(start <= " + slutt + " AND slutt >= " + slutt + ") OR " \
                        "(start > " + start + " AND slutt < " + slutt + ")))")

    return data

def getAllGroups():
    data = executeSQL("SELECT gruppeID, navn FROM GRUPPE")
    return data

def getSpecificGroup(navn):
    data = executeSQL("SELECT gruppeID FROM GRUPPE WHERE navn = " + navn)
    return data

def createGroup(navn, supergruppeID):
    data = executeSQL(  "INSERT INTO GRUPPE (navn, supergruppeID) VALUES (" \
                         + navn + ", " + supergruppeID + ")")

    return data

def createGroupMember(brukerID, gruppeID, admin):
    data = executeSQL(  "INSERT INTO GRUPPEBRUKER (brukerID, gruppeID, admin) VALUES (" \
                         + brukerID + ", " + gruppeID + ", " + admin + ")")

    return data

def createAppointment(navn, start, slutt, beskrivelse, sted, moteromID):
    data = executeSQL(  "INSERT INTO AVTALE (navn, start, slutt, beskrivelse, sted, moteromID) VALUES (" \
                        + navn + ", " + start + ", " + slutt + ", " + beskrivelse + ", " + sted + ", " + moteromID + ")")
    return data

def createAppointmentMember(brukerID, avtaleID, deltar, admin):
    data = executeSQL( "INSERT INTO AVTALEBRUKER (brukerID, avtaleID, deltar, admin) VALUES (" \
                         + brukerID + ", " + avtaleID + ", " + deltar +  ", " + admin + ")")
    return data


def getAppointments(brukerID, start, slutt):
    data = executeSQL(  "SELECT * FROM AVTALE " \
                        "WHERE (start >= " + start + " AND slutt < " + slutt + ") AND avtaleID IN (" \
                        "SELECT avtaleID FROM AVTALEBRUKER WHERE brukerID = " + brukerID + ")")
    return data

def getSpecificAppointment(avtaleID):
    data = executeSQL(  "SELECT * FROM AVTALE WHERE avtaleID = " + avtaleID)

    return data


def executeSQL(sqlq):
    db = MySQLdb.connect(   host =   "localhost", # host
                            user =   "root", # username
                            passwd = "felles35_15", # password
                            db =     "kalenderdatabase") # name of the database

    # Creation of cursor object. This lets us execute all the queries we need
    cur = db.cursor()

    try :
        # SQL-queries
        cur.execute(sqlq)

        db.commit()

    except MySQLdb.IntegrityError, message:
        errorcode = message[0]	# get MySQL error code
        if errorcode == 1062 :	# if duplicate
            return "duplicateEntry"




    # Parse query-results to json-dump
    results = dictfetchall(cur)
    json_results = str(json.dumps(results))

    if (json_results == "[]"):
        json_results = '[{"lastInsertID": ' + str(cur.lastrowid) + '}]'

    return json_results


def dictfetchall(cursor):
    """Returns all rows from a cursor as a list of dicts"""
    desc = cursor.description
    return [dict(itertools.izip([col[0] for col in desc], row))
            for row in cursor.fetchall()]

if debug:
    data = "k2pj39as9d0uo34jkh41#('markusra', 'test')"
    interpreter(data)
