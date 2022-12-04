from socket import *
import random
from tabulate import tabulate

actions = ['Rock', 'Paper', 'Scissor', 'Lizard', 'Spock', 'Draw']
HOST, PORT = "127.0.0.1", 40000


def converteIntToBytes(a):
    return a.to_bytes(3, 'big')

def converteBytesToInt(b):
    return int.from_bytes(b, 'big')

def heuristica():
    result = random.randrange(0,5)
    return result


def checkWin(a,b):
    if (a==b):
        return 5
    if( a == 0 and b == 1):
        return b
    if( a == 0 and b == 2):
        return a
    if( a == 0 and b == 3):
        return a
    if( a == 0 and b == 4):
        return b
    if( a == 1 and b == 2):
        return b
    if( a == 1 and b == 3):
        return b
    if( a == 1 and b == 4):
        return a
    if( a == 2 and b == 3):
        return b
    if( a == 2 and b == 4):
        return b
    if( a == 3 and b == 4):
        return b
    if( b == 0 and a == 2):
        return b
    if( b == 0 and a == 3):
        return b
    if( b == 0 and a == 4):
        return a
    if( b == 1 and a == 2):
        return a
    if( b == 1 and a == 3):
        return a
    if( b == 1 and a == 4):
        return b
    if( b == 2 and a == 3):
        return a
    if( b == 2 and a == 4):
        return a
    if( b == 3 and a == 4):
        return a
    
def printGameStatus(listPlays):
    print(tabulate(listPlays, headers=["Round","PythonPlayer", "JavaPlayer", "Status"]))
    print("\n\n")


with socket(AF_INET, SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    conn, addr = s.accept()
    roundCount = 0
    plays = []

    with conn:
        print(f"Endereço do Cliente {addr}")
        while True:
            m = str(heuristica()) + '\n'
            data = conn.recv(1024)
            if not data or roundCount == 15:
                break
            n = converteBytesToInt(data) - 48
            conn.sendall(m.encode())
            j = int(m)
            print(j)
            plays.append([roundCount,actions[j],actions[n],actions[checkWin(j,n)]])
            printGameStatus(plays)

            roundCount = roundCount+1