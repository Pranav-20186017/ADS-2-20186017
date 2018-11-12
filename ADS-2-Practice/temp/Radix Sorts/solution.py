"""
String Sort

"""
N = int(input())
A = []
for i in range(N):
    A.append((input()))
print('[%s]' % ', '.join(map(str, A)))
