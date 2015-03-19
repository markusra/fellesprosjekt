# SQL-metodene
import MySQLdb
import itertools
import json
import time

debug = False
reply = ''
splitChar = '#'

curMilliTime = lambda: int(round(time.time() * 1000))

lastChangedTime = 0

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

        lastChangedTime = curMilliTime
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
        varsel = str(data[4])

        reply = createAppointmentMember(brukerID, avtaleID, deltar, admin, varsel)

    elif (command == 'f2ks94lfurb68z52k9ah'): # GET APPOINTMENTS
        brukerID = str(data[0])
        start = str(data[1])
        slutt = str(data[2])

        reply = getAppointments(brukerID, start, slutt)

    elif (command == 's8cj2jak602lfun4h6z8'): # GET SPECIFIC APPOINTMENT
        avtaleID = str(data)

        reply = getSpecificAppointment(avtaleID)

    elif (command == 'p7zf26a5hem79gul4hy7'): # GET ALL MEMBER GROUPS
        brukerID = str(data)

        reply = getAllMemberGroups(brukerID)

    elif (command == 'a74hn9kl2k4lxn57cgh3'): # GET GROUP MEMBERS
        navn = "'" + str(data) + "'"

        reply = getGroupMembers(navn)

    elif (command == 'p7sg3bzv32jtuhf7k6nr'):  # GET APPOINTMENT MEMBER
        brukerID = str(data[0])
        avtaleID = str(data[1])

        reply = getAppointmentMember(brukerID, avtaleID)

    elif (command == 'q8sh3nd8vhe0hit43kxh'): # UPDATE AVTALEBRUKER
        brukerID = str(data[0])
        avtaleID = str(data[1])
        deltar = str(data[2])
        varsel = str(data[3])

        reply = updateAppointmentUser(brukerID, avtaleID, deltar, varsel)

    elif (command == 'x82m4jf7ch4dk7h6fn4k'): # GET ATTENDANCE AND ALERT
        brukerID = str(data[0])
        avtaleID = str(data[1])

        reply = getAttendanceAlert(brukerID, avtaleID)

    elif (command == 'p7sg3nj86sk64hn2loc5'): # GET ATTENDING USERS
        avtaleID = str(data)

        reply = getAttendingUsers(avtaleID)

    elif (command == 'z83mdk48dhw7sn51koel'): # DELE APPOINTMENT
        avtaleID = str(data)

        reply = deleteAppointment(avtaleID)


    elif (command == 'q83j5c8m3k0s3fie8d2h'): # GET JOINED MEMBERS
        gruppeNavn = str(data)

        reply = getJoinedMembers(gruppeNavn)


    elif (command == 't3js947ch4n57ak92lem'): # GET ALL APPOINTMENTS FOR ADMIN
        brukerID = str(data)

        reply = getAllAppointmentsForAdmin(brukerID)

    elif (command == 'k39sh21sk97dhek8usya'): # GET ROOM WITH ID
        moteromID = str(data)

        reply = getRoomWithID(moteromID)


    elif (command == 'v4nsk2jd8ut67xmf8ke3'): # GET CHANGE STATUS
        lastUpdatedTime = long(data)

        global lastChangedTime

        if (lastUpdatedTime < lastChangedTime):
            reply = '[{"didChange": ' + "\"Yes\"" + '}]'
        else:
            reply = '[{"didChange": ' + "\"No\"" + '}]'


    elif (command == 'a83kdu8dbe5lg7c39qu3'): # GET APPOINTMENTS COUNT
        reply = getAppointmentsCount()

    elif (command == 'b37chw89sk3gy2nfy7i6'): # GET GROUPS COUNT
        reply = getGroupsCount()

    elif (command == 'c3b8kam28duh3j7cg4n7'): # GET MEMBERS COUNT
        reply = getMembersCount()

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

def getAppointmentMember(brukerID, avtaleID):
    data = executeSQL("SELECT * FROM AVTALEBRUKER WHERE avtaleID = " + avtaleID + " AND brukerID = " + brukerID)

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

def getAllMemberGroups(brukerID):
    print  "SELECT gruppeID, navn FROM GRUPPE " \
                        "WHERE gruppeID IN (" \
                        "SELECT gruppeID FROM GRUPPEBRUKER WHERE brukerID = " + brukerID + ")"
    data = executeSQL(  "SELECT gruppeID, navn FROM GRUPPE " \
                        "WHERE gruppeID IN (" \
                        "SELECT gruppeID FROM GRUPPEBRUKER WHERE brukerID = " + brukerID + ")")
    return data

def getGroupMembers(navn):
    data = executeSQL(  "SELECT * FROM GRUPPEBRUKER " \
                        "WHERE gruppeID IN (" \
                        "SELECT gruppeID FROM GRUPPE WHERE navn = " + navn + ")")
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

def createAppointmentMember(brukerID, avtaleID, deltar, admin, varsel):
    data = executeSQL( "INSERT INTO AVTALEBRUKER (brukerID, avtaleID, deltar, admin, varsel) VALUES (" \
                         + brukerID + ", " + avtaleID + ", " + deltar +  ", " + admin +  ", " + varsel + ")")
    return data


def getAppointments(brukerID, start, slutt):
    data = executeSQL(  "SELECT *, AVTALE.navn AS avtaleNavn, MOTEROM.navn AS moterom FROM AVTALE " \
                        "INNER JOIN MOTEROM " \
                        "ON AVTALE.moteromID = MOTEROM.moteromID " \
                        "WHERE (start >= " + start + " AND slutt < " + slutt + ") AND avtaleID IN (" \
                        "SELECT avtaleID FROM AVTALEBRUKER WHERE brukerID = " + brukerID + ")")
    return data

def updateAppointmentUser(brukerID, avtaleID, deltar, varsel):
    data = executeSQL(  "UPDATE AVTALEBRUKER" \
                        " SET deltar = " + deltar + ", varsel = " + varsel + \
                        " WHERE avtaleID = " + avtaleID + " AND brukerID =" + brukerID)
    return data

def getAttendanceAlert(brukerID, avtaleID):
    data = executeSQL(  "SELECT deltar, varsel, admin FROM AVTALEBRUKER WHERE brukerID = " \
                        + brukerID + " AND avtaleID = " + avtaleID)

    return data

def getAttendingUsers(avtaleID):
    data = executeSQL(  "SELECT * " \
                        "FROM AVTALEBRUKER " \
                        "INNER JOIN BRUKER " \
                        "ON AVTALEBRUKER.brukerID = BRUKER.brukerID " \
                        "WHERE avtaleID = " + avtaleID )

    return data

def getJoinedMembers(gruppeNavn):
    data = executeSQL(  "SELECT * " \
                        "FROM GRUPPEBRUKER " \
                        "INNER JOIN BRUKER " \
                        "ON GRUPPEBRUKER.brukerID = BRUKER.brukerID " \
                        "INNER JOIN GRUPPE " \
                        "ON GRUPPE.gruppeID = GRUPPEBRUKER.gruppeID " \
                        "WHERE GRUPPE.navn = '" + gruppeNavn + "'")

    return data


def getAllAppointmentsForAdmin(brukerID):
    data = executeSQL(  "SELECT AVTALE.avtaleID, AVTALE.navn, admin, deltar " \
                        "FROM AVTALE " \
                        "INNER JOIN AVTALEBRUKER " \
                        "ON AVTALE.avtaleID = AVTALEBRUKER.avtaleID " \
                        "INNER JOIN BRUKER " \
                        "ON BRUKER.brukerID = AVTALEBRUKER.brukerID " \
                        "WHERE BRUKER.brukerID = " + brukerID)

    return data

def deleteAppointment(avtaleID):
    data = executeSQL(  "DELETE FROM AVTALE WHERE avtaleID = " + avtaleID )

    return data

def getRoomWithID(moteromID):
    data = executeSQL(  "SELECT navn FROM MOTEROM WHERE moteromID = " + moteromID )

    return data

def getAppointmentsCount():
    data = executeSQL("SELECT count(avtaleID) FROM AVTALE")

    return data

def getGroupsCount():
    data = executeSQL("SELECT count(gruppeID) FROM GRUPPE")

    return data

def getMembersCount():
    data = executeSQL("SELECT count(brukerID) FROM BRUKER")

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
