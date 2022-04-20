from typing import List


class _MaxQue:

    def __init__(self):
        self.que = []
        self.max_que = []
    def __len__(self):
        return len(self.que)
    def push(self, val: int):
        self.que.append(val)
        if not self.max_que or val >= self.max_que[-1]:
            self.max_que.append(val)

    def pop(self) -> int:
        val: int = self.que.pop(0)
        if val == self.max_que[0]:
            self.max_que.pop(0)
        return val

    def max(self) -> int:
        return self.max_que[-1]


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        que = _MaxQue()
        # init the mono que
        for i in range(k - 1, len(nums)):
            while len(que)<k:
                

        return result
