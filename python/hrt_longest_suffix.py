from typing import *
import sys
class Solution :
    def longestSuffix (self, inputs:List[str])->str:
        simplies =[]
        for input in inputs:
            simplies.append(self.simplify(input))
        
        min_len = sys.maxsize
        for s in simplies:
            min_len = min(min_len, len(s))
        res =[]
        i = 1
        while i<=min_len:
            token = simplies[0][-i]
            all_same = True
            for s in simplies:
                if not s[-i].__eq__(token):
                    all_same = False
                    break
            if all_same:
                res.insert(0, token)
                i+=1
            else:
                break
        return "" if res.__len__ ==0 else '/'+'/'.join(res) 

    def simplify(self, input:str)->List[str]:
        tokens =input.split('/')
        stack =[]
        for token in tokens:
            if(token.__eq__('..')):
                stack.pop()
            elif len(token) > 0:
                stack.append(token)    
        return stack

if __name__ == '__main__' :
     paths = ['/a/f1/../f1/a/leaf.txt', '/b/f2/../f1/a/leaf.txt', '/a/f3/f1/f1/../a/leaf.txt']
     print(Solution().longestSuffix(paths))