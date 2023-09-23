N = 10000
p = [[0]*N for _ in range(N+1)]
for i in range(N):
    p[0][i] = 1
for i in range(1, N):
    for j in range(1, i+1):
        p[i][j] = p[i][j-1] + p[i-j][min(j, i-j)]

    if p[i][i] % 10000 == 0:
        print(i, p[i][i])