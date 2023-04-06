from typing import List

class Solution:
    def encrpyt(self, m: List[List[str]]) -> None:
        n = int (len(m)/2)
        for i in range(n):
            for j in range(n):
                self.swap(m, i*2,j, 2*i+1, j)
        
        #diano swap
        for j in range(n):
            for i in range(j, n):
                #  is the base of the line
                self.swap(m, 2*i, j, j*2, i)
                self.swap(m, 2*i+1, j, j*2+1, i)
        
        
    def swap (self, m: List[List[str]], x1: int, y1: int, x2:int, y2:int) -> None:
        temp = m[x1][y1]
        m[x1][y1]=m[x2][y2]
        m[x2][y2]=temp

def genMatrix(n: int) -> List[List[str]]:
    ret =[]
    base = ord('a')
    for _ in range(2*n):
        line =[]
        for _ in range(n):
            line.append(chr(base))
            base+=1
        ret.append(line)
    return ret    

if __name__=='__main__':
     matrix = genMatrix(3)
     Solution().encrpyt(matrix)
     print(matrix)