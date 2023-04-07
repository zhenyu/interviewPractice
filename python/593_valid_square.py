from typing import *
class Solution:
    def validSquare(self, p1: List[int], p2: List[int], p3: List[int], p4: List[int]) -> bool:
        p = [p1,p2,p3,p4]
        p = sorted(p, key=lambda point: (point[0],point[1]))
        return dist(p[0],p[1])!=0 and dist(p[0],p[1])==dist(p[0],p[2])  and dist(p[0],p[1])==dist(p[1],p[3]) and dist(p[0],p[1])==dist(p[2],p[3]) and dist(p[0],p[3])==dist(p[1],p[2])
         
if __name__=='__main__':
    print (Solution().validSquare([0,0], [1,1], [1,0],[0,1]))