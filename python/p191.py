
never_late = [1, 0, 0]
once_late = [0, 0, 0]


N = 30


for i in range(0, N):

    new_never_late = [
        never_late[0]+never_late[1]+never_late[2],
        never_late[0],
        never_late[1]
    ]
    new_once_late = [
        never_late[0] + never_late[1] + never_late[2] + once_late[0] + once_late[1] + once_late[2],
        once_late[0],
        once_late[1]
    ]
    never_late = new_never_late
    once_late = new_once_late
    #print(never_late, once_late)


print(sum(never_late) + sum(once_late))