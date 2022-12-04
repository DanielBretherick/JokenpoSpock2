from socket import *
import random

HOST, PORT = "127.0.0.1", 40000

def converteIntToBytes(a):
    return a.to_bytes(3, 'big')

def converteBytesToInt(b):
    return int.from_bytes(b, 'big')


with socket(AF_INET, SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    roundCount = 0
    while True:
        if roundCount == 15:
            break
        n = random.randrange(0,5)
        s.sendall(converteIntToBytes(n))
        print("Enviei:", n)
        data = s.recv(1024)
        result = converteBytesToInt(data)
        print("Recebi:",result)
        roundCount = roundCount+1
        