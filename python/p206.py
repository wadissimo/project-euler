def check(n):
    s = n*n
    for i in range(9, 0, -1):
        if s % 10 != i:
            return False
        s = s//100
    return True


print(check(138901917))
for i in range(10000000,15000000):
    if check(i*10+3) :
        print("found:", i*10+3)
    if check(i * 10 + 7):
        print("found:", i * 10 + 7)
