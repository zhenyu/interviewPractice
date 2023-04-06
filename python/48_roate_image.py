from typing import *
class Solution:
    def rotate(self, m: List[List[int]]) -> None:
        n = len(m)
        c = int((n+1)/2)
        for i in range(c):
            for j in range (i, n-1-i): 
                #counter clock
                temp = m[i][j]
                m[i][j] = m[n-1-j][i]
                m[n-1-j][i] = m[n-1-i][n-1-j]
                m[n-1-i][n-1-j] = m[j][n-1-i]
                m[j][n-1-i] = temp

if __name__=='__main__':
     matrix = [[1,2,3],[4,5,6],[7,8,9]]
     Solution().rotate(matrix)
     print(matrix)