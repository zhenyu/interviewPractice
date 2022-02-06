from typing import List


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        que = []
        # init the mono que
        result = []
        for i in range(k - 1, len(nums)):
            start = i - k + 1
            # pop the que
            if que and que[0] < i - k + 1:
                start = que.pop(0) + 1
            if que:
                start = i
            while start <= i:
                if not que or nums[que[-1]] <= nums[start]:
                    que.append(start)
                start += 1
            result.append(que[-1])
        return result
