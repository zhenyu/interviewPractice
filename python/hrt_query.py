from typing import *
class Solution:
    self.b 
    self.a
    self.value_to_index = {} 
    def __init__(self, a:List[int], b:List[int]) -> None:
        self.b = b
        self.a = a
        for i in range(len(a)):
            self.value_to_index.setdefault(a[i], {}).add(i)
        
    def query(self, q:List[int])->int:
        ret =0
        if q[0]==0:
            old_value = self.a[q[1]]
            self.value_to_index.get(old_value).remove(q[1])
            self.a[q[1]]=q[2]
            self.value_to_index.setdefault(a[q[1]], {}).add(q[1])
        else:
            for i in range(len(self.b)):
                v2s= self.value_to_index.getdefault(q[1]-self.b[i], {})
                ret+=len(v2s)
        return ret
                