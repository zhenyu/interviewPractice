from typing import *
class Solution:
   
    def __init__(self, a:List[int], b:List[int]) -> None:
        self.b = b
        self.a = a
        self.value_to_index = {}
        for i in range(len(a)):
            self.value_to_index.setdefault(self.a[i], set()).add(i)
        
    def query(self, q:List[int])->int:
        ret =0
        if q[0]==0:
            old_value = self.a[q[1]]
            self.value_to_index.get(old_value).remove(q[1])
            self.a[q[1]]=q[2]
            self.value_to_index.setdefault(self.a[q[1]], set()).add(q[1])
        else:
            for i in range(len(self.b)):
                v2s= self.value_to_index.get(q[1]-self.b[i])
                ret+=len(v2s)
        return ret

if __name__ == '__main__':
    s = Solution([1,2,3,4,5], [6,7,8,9])
    print(s.query([1,10]))
    
    print(s.query([0,4,9]))
    print(s.query([1,10]))
    
    print(s.query([0,4,1]))
    print(s.query([1,10]))

    print(s.query([0,2,5]))
    print(s.query([0,4,5]))
    print(s.query([1,10]))