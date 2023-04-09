from typing import *
class Solution(object):
    def fill_first(self, m:List[List[int]])->Tuple(int):
        left_mosts = []
        pre_sums =[]
        for line in m:
            sum =0
            pre_sum =[]
            left_most = None
            for i in range(len(line)):
                sum+line[i]
                pre_sum.append(sum)
                if left_most is None and line[m]==1:
                    left_most = i
            left_mosts.append[left_most]
            pre_sums.append(pre_sum)

        min  = 0
        base = None
        for left_most in left_mosts:
            if left_most is not None:
                base = left_most
                break
        
        for pre_sum in pre_sums:
            min += pre_sum[base-1]
        max = 0
        base = left_most[len]
        return (min, max)