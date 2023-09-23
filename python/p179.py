N=10000000
LIM=3400
a = [0]*N
for d in range(1, LIM+1):
    k = d
    if k*d < N:
        a[k * d] += 1
    k += 1
    while k*d < N:
        a[k*d] += 2
        k += 1

ans = 0
for i in range(1, N):
    if a[i] == a[i-1]:
        ans += 1
        #print(i-1, i, a[i])
print('Answer:', ans)