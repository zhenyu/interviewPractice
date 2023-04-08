from typing import *
class Solution(object):
    def query(self, n:list[int])->List[bool]:
        #find the max
        n_max=max(n)
        f1=0
        f2=1
        fs=[f1, f2]
        while f1+f2<=n_max:
            temp = f1
            f1=f2
            f2=f2+temp
            fs.append(f2)
        p =set(fs)
        
        for i in range(len(fs)):
            if fs[i]*2>n_max:
                break
            for j in range(i, len(fs)):
                if(fs[i]+fs[j]>n_max):
                    break
                p.add(fs[i]+fs[j])
        
        ret=[]
        for num in n:
            if  num in p:
                ret.append(True)
            else:
                ret.append(False)
        return ret

if __name__ == '__main__':
    ret = Solution().query([2, 17,11,10])
    print (','.join([str(r) for r in ret]))