from typing import List
class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        ret = []
        cur = 1
        count = 0
        while count < n:
            ret.append(cur)
            count += 1
            if cur * 10 <= n:
                cur = cur * 10
            else:
                while cur + 1 > n or cur % 10 == 9:
                    cur = int(cur / 10)
                cur = cur + 1
        return ret
