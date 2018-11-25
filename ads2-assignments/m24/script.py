import os
import subprocess
import numpy as np
from statistics import mean
import matplotlib.pyplot as plt 
def bm():
    mill_ar = []
    cmd = "java BoyerMoore"
    x = 100
    while(x > 0):
        returned_output = subprocess.check_output(cmd)
        returned_output =  returned_output.decode("utf-8")
        mill_ar.append(int(returned_output))
        x = x -1
    print("------------BoyerMoore-------------")
    n = np.array(mill_ar)
    print(mean(n))
    print(np.std(n))
    print("----------------------------------")
    return n
def brute():
    mill_ar = []
    cmd = "java Brute"
    x = 100
    while(x > 0):
        returned_output = subprocess.check_output(cmd)
        returned_output =  returned_output.decode("utf-8")
        mill_ar.append(int(returned_output))
        x = x -1
    print("------------Bruteforce-------------")
    n = np.array(mill_ar)
    print(mean(n))
    print(np.std(n))
    print("----------------------------------")
    return n
def rk():
    mill_ar = []
    cmd = "java RabinKarp"
    x = 100
    while(x > 0):
        returned_output = subprocess.check_output(cmd)
        returned_output =  returned_output.decode("utf-8")
        mill_ar.append(int(returned_output))
        x = x -1
    print("------------Rabin Karp-------------")
    n = np.array(mill_ar)
    print(mean(n))
    print(np.std(n))
    print("----------------------------------")
    return n
def kmp():
    mill_ar = []
    cmd = "java KMP"
    x = 100
    while(x > 0):
        returned_output = subprocess.check_output(cmd)
        returned_output =  returned_output.decode("utf-8")
        mill_ar.append(int(returned_output))
        x = x -1
    print("------------KMP-------------")
    n = np.array(mill_ar)
    print(mean(n))
    print(np.std(n))
    print("----------------------------------")
    return n
def main():
    x1 = []
    for i in range(1,101):
        x1.append(i)
    y1 = bm()
    y2 = brute()
    y3 = rk()
    y4 = kmp()
    plt.scatter(x1, y1, label = "BoyerMoore")
    plt.scatter(x1, y2, label = "Bruteforce")
    plt.scatter(x1, y3, label = "RabinKarp")
    plt.scatter(x1, y4, label = "KMP")
    plt.xlabel('x - axis')
    plt.ylabel('time in milli seconds')
    plt.title('Analysis of Substring Search Algorithms')
    plt.legend() 
    plt.show()
if __name__ == "__main__":
    main()

