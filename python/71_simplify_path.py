from typing import *
class Solution:
    def simplifyPath(self, path: str) -> str:
        tokens = path.split('/')
        res=[]
        for t in tokens:
            if t.__eq__('..') :
                if len(res) >0:
                    res.pop()
            elif len(t)>0 and not t.__eq__('.'):
                res.append(t)
        return '/'+'/'.join(res)
