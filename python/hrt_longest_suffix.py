from typing import *
import sys
class Solution :
    def longestSuffix (inputs:List[str])->str:
        simplies =[]
        for input in inputs:
            simplies.append(self.simplify(input))
        
        int min_len = sys.maxsize
        for s in simplies:
            min_len = min(min_len, len(s))
        res =[]
        int i = 0
        while i<min_len:
            bool allsame = True
            token = simplies[0][-(i+1)]
            for s in simplies:
                if
            i+=1
    def simplify(self, input:str)->List[str]:
        tokens =input.split('/')
        stack =[]
        for token in tokens:
            if(token.__eq__('..')):
                stack.pop()
            else:
                stack.append(token)    