import hashlib

password = "1234"
hashed_password = hashlib.sha1(password.encode()).hexdigest()
print(hashed_password)
