import sys
from typing import *

class Solution:
    def minimumDifference(self, nums: List[int]) -> int:
        n = int(len(nums)/2)
        cache ={0 : 0}
        total = 0
        ret = sys.maxsize
        for num in nums:
            total+=num
        for i in range (1, pow(2, 2*n)-pow(2, n)+1):
            in_collection  =  bin(i).count("1")
            if in_collection > n:
                continue
            if in_collection ==1:
                 cache ={0 : 0}
       
            j = i&(-i)
            new_index= self.indexofnum(j)
            # 弄成0
            j =~j
            prev = i&j
            value = cache[prev]+nums[new_index]
            cache[i] = value
            if in_collection == n:
                delta =abs(total-2*value)
                if delta< ret:
                    ret=delta
        return ret        

    def indexofnum(self, num):
        i = 0
        while ((num>>i)&1) == 0:
            i+=1
        return i      

if __name__ == "__main__":
    print(Solution().minimumDifference([2,-1,0,4,-2,-9]))