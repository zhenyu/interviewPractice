from typing import *
import bisect
class Solution(object):
    def __init__(self) -> None:
        self.o = [[-1,-1]]
    def allocate(self)->int:
        c = self.o[0]
        c[1]+=1
        ret = c[1]
        if len(self.o)>1 and self.o[1][0]==c[1]+1:
            n = self.o[1]
            n[0]=c[0]
            self.o.pop(0)        
        return ret       

    def release(self,id)->None:
        index = bisect.bisect_left(self.o, id, key= lambda p: p[1])
        if index<len(self.o):
            c = self.o[index]
            if c[0]==c[1] and c[0]==id:
                self.o = self.o[0:index]+self.o[index+1:-1]
            elif c[0]==id:
                c[0]+=1
            elif c[1]==id:
                c[1]-=1
            else:
                p=[c[0], id-1]
                c[0]=id+1
                self.o.insert(index, p)

if __name__ == '__main__':
    a = Solution()
    for i in  range(6):
        print(a.allocate())
    a.release(3)
    a.release(0)
    a.release(6)
    print(a.allocate())
    print(a.allocate())
    print(a.allocate())
    print(a.allocate())