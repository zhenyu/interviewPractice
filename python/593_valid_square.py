from typing import *
class Solution:
    def validSquare(self, p1: List[int], p2: List[int], p3: List[int], p4: List[int]) -> bool:
        points = [p1,p2,p3,p4]
        points = sorted(points, key=lambda point: (point[0],point[1]))
        dist(p1,p2)!=0 and dist(p1,p2)==dist(p1, p3) and dist(p1,p2)==dist(p2, p4) and dist(p1,p2)==dist(p3, p4) and dist(p2,p3)==dist(p1, p4)    
    
    def dist(p1:List[int], p2: List[int])->int:
        (p1[0]-p2[0])* (p1[0]-p2[0])+ (p1[1]-p2[1])* (p1[1]-p2[1])
         
if __name__ == 'main':
    print (Solution().validSquare([0,0], [1,1], [1,0],[0,1]))