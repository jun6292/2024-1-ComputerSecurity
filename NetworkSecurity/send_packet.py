from scapy.all import *

def send_packet():
    packet = IP(dst="192.168.0.1")/ICMP()/"test"
    send(packet)

if __name__ == "__main__":
    send_packet()

