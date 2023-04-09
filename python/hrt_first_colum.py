from typing import *
class Solution(object):
    def fill_first(self, m:List[List[int]])->Tuple[int, int]:
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
                    parts.append((part, left_mosts))
                    part = []
                    left_mosts = []
            else:
                part.append(line)
                left_mosts.append (left_most)
        
        if len(part)>0:
            parts.append((part, left_mosts))
        r_min =0
        r_max =0
        for t in parts:
            p_min, p_max = self.fill_part(t[0], t[1]) 
            r_min+=p_min
            r_max+=p_max
        return (r_min, r_max)

    def fill_part(self, part:List[List[int]], left_mosts: List[int])->Tuple[int, int]:        

        
        r_max = 0
        base_max = 0
        #max
        for row_index in range(len(part)):
            row = part[row_index]
            base_max = max(base_max, left_mosts[row_index])
            sum =0
            for c in range (base_max):
                sum+=(1-row[c])
            r_max+=sum    
        
        # min
        base_min = len(part[0])+1
        r_min  = 0
        row_index =0 
        for row_index in range (len(part)):
            if left_mosts[row_index]!=0 and left_mosts[row_index]<base_min:
                base_min = left_mosts[row_index]
                break
        if base_min<len(part[0])+1:
            pre_row = None
            while row_index < len(part):
                row = part[row_index]
                if pre_row is not None:
                    for c in range(base_min):
                        if row[c]==0 and pre_row[c]==1:
                            row[c]=1
                sum =0 
                for c in range (base_min):
                    sum += (1-row[c])
                r_min+=sum 
                row_index+=1
        
        return (r_min, r_max)

if __name__ == '__main__':
    f  = Solution()
    m =[[0, 0,1 ,0], [1, 0, 0, 1],[1, 1, 1,1]]#**[0,0 ,1 ,0], [0, 0, 0, 1]]
    r = f.fill_first(m)
    print(r)