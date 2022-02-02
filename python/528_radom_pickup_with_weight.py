import random
class Solution:
    def __init__(self, w: List[int]):
        self.list=[]
        start = 0
        for weight in w:
            self.list.append(start)
            start+=weight
        self.max =start-1
    def pickIndex(self) -> int:
        r=random.randint(0, self.max)
        #find the first element which is less or equal to the r
        return bisect_right(self.list, r) -1

#if __name__=='__main__':
# Your Solution object will be instantiated and called as such:
#    obj = Solution(w)
#    param_1 = obj.pickIndex()