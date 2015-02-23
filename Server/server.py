#!/usr/bin/python
import MySQLdb

db = MySQLdb.connect(   host =   "localhost", # host
                        user =   "root", # username
                        passwd = "felles35_15", # password
                        db =     "bokbase") # name of the data base

# Creation of cursor object. This lets us execute all the queries we need
cur = db.cursor()

#SQL-status
print 'Ready to execute SQL-queries...'

# SQL-queries
print 'Executes query: SELECT * FROM BOK'
cur.execute("SELECT * FROM BOK")

print 'Fetch reply...'
print '----------------------------'
# Print data
for row in cur.fetchall() :
    streng = str(row[0]) + ' - ' + str(row[1]) + ' - ' + str(row[2])
    print streng
