from scapy.all import *

def custom_traceroute(dest_ip):
    max_ttl = 30
    for ttl in range(1, max_ttl + 1):
        # IP 헤더에 TTL 설정, ICMP 에코 요청 패킷 생성
        packet = IP(dst=dest_ip, ttl=ttl) / ICMP()
        # 패킷 전송 후 응답 수신
        reply = sr1(packet, timeout=2, verbose=0)

        if reply is None:
            print(f"{ttl} hops away: Request timed out")
        else:
            # 응답 받은 IP 주소 출력
            print(f"{ttl} hops away: {reply.src}")

            # ICMP 에코 응답 타입 검사 (type==0)
            if reply.type == 0:
                print("Reached destination")
                break

if __name__ == "__main__":
    dest_ip = input("Enter the IP address to trace: ")
    custom_traceroute(dest_ip)
