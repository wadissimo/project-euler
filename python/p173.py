import math
N = 100


def factors(n):
    d = 1
    while d*d < n:
        if n%d == 0:
            yield d, n//d
        d+=1

count = 0
for n in range(2, 1000001):
    for x, y in factors(n):
        if x%2 == y%2 and ((x+y)//2)%2 == ((y-x)//2)%2:
            count += 1
            #print(n, x,y)

print(count)