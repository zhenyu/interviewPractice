from typing import *
import bisect
class Solution(object):
    def __init__(self, n, m) -> None:
        self.rows =  list(range (1, n+1))
        self.cols =  list(range(1, m+1))
    def query(self, qs : List[List[int]]) -> int:
        ret=[]
        for  q in qs:
            if q[0]==0:
                ret.append(self.cols[0]*self.rows[0])
            else: 
                if q[0]==1:
                    self.rows = self.search_remove(self.rows, q[1])
                if q[0]==2:
                    self.cols = self.search_remove(self.cols, q[1])
        return ret
    def search_remove(self, input: List[int], value: int) -> List[int]:
        i = bisect.bisect_left(input, value)
        if i<len(input) and input[i]==value:
            return input[0: i]+ input[i+1:]
        
if __name__ == '__main__':
    m = Solution(3, 4)
    ret = m.query([[0], [1,2],[0],[2,1], [0],[1,1],[0]])
    print(','.join([str(r) for r in ret]))