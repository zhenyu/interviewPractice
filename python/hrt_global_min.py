from typing import *
import bisect
class Solution(object):
    def __init__(self, n, m) -> None:
        self.rows =  list(range (1, n+1))
        self.cols =  list(range(1, m+1))
    def query(self, q : List[int]) -> int:
        if q[0]==1:
            self.rows = self.search_remove(self.rows, q[1])
        if q[0]==2:
            self.cols = self.search_remove(self.cols, q[1])
        return self.cols[0]*self.rows[]
    def search_remove(self, input: List[int], value: int) -> List[int]:
        i = bisect.bisect_left(input, value)
        if i<len(value) and input[i]==value:
            return input[0: i]+ input[i+1:-1]