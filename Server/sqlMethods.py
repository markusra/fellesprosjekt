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

        reply = str(login(username, password))

        if debug:
            print username + ' : ' + password
            print reply

    elif (command == 'y2sg59sleuc5el70sjr3'): # CREATE USER
        username = data[0]
        password = data[1]
        epost = data[2]
        fornavn = data[3]
        etternavn = data[4]

    return command + splitChar + reply

def validateUser(data):
    if data != []:
        return True
    return False

def login(username, password):
    data = executeSQL("SELECT brukernavn, passord FROM BRUKER WHERE brukernavn ='" \
    + username + "' AND passord ='" + password + "'")

    #if (validateUser(data)):
    #    return True

    return data

def createUser(username, password, email, firstname, lastname):
    data = executeSQL("INSERT INTO BRUKER (brukernavn, passord, epost, fornavn, etternavn) VALUES ('" \
    + username + "', '" + password + "', '" + email + "', '" + firstname + "', '" + lastname + "'")

    #print data

    if (validateUser(data)):
        return True

    return False

def executeSQL(sqlq):
    db = MySQLdb.connect(   host =   "localhost", # host
                            user =   "root", # username
                            passwd = "felles35_15", # password
                            db =     "kalenderdatabase") # name of the database

    # Creation of cursor object. This lets us execute all the queries we need
    cur = db.cursor()

    # SQL-queries
    cur.execute(sqlq)

    # Parse query-results to json-dump
    results = dictfetchall(cur)
    json_results = json.dumps(results)

    return json_results


def dictfetchall(cursor):
    """Returns all rows from a cursor as a list of dicts"""
    desc = cursor.description
    return [dict(itertools.izip([col[0] for col in desc], row))
            for row in cursor.fetchall()]

if debug:
    data = "k2pj39as9d0uo34jkh41#('markusra', 'test')"
    interpreter(data)
