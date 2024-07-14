# XOR two bytearrays
def xor(first, second):
    return bytearray(x ^ y for x, y in zip(first, second))

P1 = bytearray("Known Message.", 'utf-8')
C1 = bytearray.fromhex("2486A3CF82D8596D0C5C219691F3")

C2 = bytearray.fromhex("198DBEC1CC9F5B671B0F2A9E96FC")

keystream = xor(P1, C1)

P2 = xor(C2, keystream)

print("P1:", P1.hex())
print("C1:", C1.hex())
print("Keystream:", keystream.hex())
print("C2:", C2.hex())
print("P2:", P2.hex())

print("P2 as string:", P2.decode('utf-8', errors='ignore'))

