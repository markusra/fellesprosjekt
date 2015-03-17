# -*- coding: utf-8 -*-
import SocketServer
from socket import error as SocketError

import errno

from sqlMethods import *

class bcolors:
    PURPLE = '\033[95m'
    OKBLUE = '\033[94m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    ENDC = '\033[0m'

    def disable(self):
        self.HEADER = ''
        self.OKBLUE = ''
        self.OKGREEN = ''
        self.WARNING = ''
        self.FAIL = ''
        self.ENDC = ''

def colorPrint(streng, color):
    print color + streng + bcolors.ENDC + "  "

debug = True
line = '---------------------------------------------------'

class ClientHandler(SocketServer.BaseRequestHandler):
    def handle(self):
        """
        This method handles the connection between a client and the server.
        """
        self.ip = self.client_address[0]
        self.port = self.client_address[1]
        self.connection = self.request


        colorPrint('Client connected @ ' + self.ip + ':' + str(self.port), bcolors.YELLOW)

        # Loop that listens for messages from the client
        counter = 0
        while True:
            try:
                received_string = self.connection.recv(4096)

                # All actions here
                if received_string:
                    colorPrint(line, bcolors.YELLOW)

                    colorPrint('Message[' + str(counter) + '] from ' \
                    + str(self.ip) + ':' + str(self.port), bcolors.OKBLUE)

                    colorPrint('REQUEST -->' + received_string, bcolors.RED)
                    counter += 1

                    svar = interpreter(received_string)

                    self.connection.sendall(svar + "\n")
                    colorPrint('REPLY --> ' + svar, bcolors.RED)

                    colorPrint(line, bcolors.YELLOW)
                else:
                    colorPrint('Client disconnected @ ' + self.ip + ':' + str(self.port), bcolors.YELLOW)
                    colorPrint(line, bcolors.YELLOW)
                    break

            except SocketError as e:
                if e.errno != errno.ECONNRESET:
                    print '!!! ERROR !!!'
                colorPrint('Connection reset by client @ ' + self.ip, bcolors.OKBLUE)




class ThreadedTCPServer(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    allow_reuse_address = True

if __name__ == "__main__":
    HOST, PORT = '', 9998
    print 'Server running...'

    #SQL-status
    print 'Ready to execute SQL-queries...'

    # Set up and initiate the TCP server
    server = ThreadedTCPServer((HOST, PORT), ClientHandler)
    server.serve_forever()
