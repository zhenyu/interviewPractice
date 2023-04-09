from typing import *
class Solution(object):
    def fill_first(self, m:List[List[int]])->Tuple(int):
        parts = []

        part =[]
        left_mosts =[]

        for line in m:
            left_most = len(line)
            all_blocks = True
            for i in range(len(line)):
                if  line[i]==1 and i<left_most:
                    left_most = i
                if line[i] == 0:
                    all_blocks = False
            
            if all_blocks:
                if len (part) >0:
                    parts.append((part, left_most))
                    part = []
                    left_mosts = []
            else:
                part.append(line)
                left_mosts.append (left_most) 

        min =0
        max =0
        for part in parts:
            p_min, p_max = self.fill_part(part) 
            min+=p_min
            max+=p_max
        return (min, max)

    def fill_part(self, part:List[List[int]], left_mosts: List[int])->Tuple(int, int):        

        min  = 0
        max = 0
        base_min = 0
        base_max = 0
        # from top to botom
        for row in range (len(part)):
            if left_mosts[row]>base_min:
                base_min = left_mosts[row]
                break
        pre_row = None
        while row < len(part):
            base_max = max(base_max, left_mosts[row])
            sum =0
            for c in range (base_max):
                sum+=1-row[c]
            max+=sum    


            if pre_row is not None:
                for c in range(base_min):
                    if row[c]==0 and pre_row[c]==1:
                        row[c]=1
            sum =0 
            for c in range (base_min):
                sum += 1-row[c]
            min+=sum 
            
        
        return (min, max)