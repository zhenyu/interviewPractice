import bisect


class KeyifyList(object):
    def __init__(self, inner, key):
        self.inner = inner
        self.key = key

    def __len__(self):
        return len(self.inner)

    def __getitem__(self, k):
        return self.key(self.inner[k])


class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        left_index = bisect.bisect_right(KeyifyList(intervals, lambda x: x[1]), newInterval[0])
        if left_index > 0 and intervals[left_index][1] == newInterval[0]:
            left_index -= 1
        right_index = bisect.bisect_left(KeyifyList(intervals, lambda x: x[0], newInterval[1]))
        if right_index < n - 1 and intervals[right_index][0] == newInterval[1]:
            right_index += 1
        return intervals[:left_index] + [min(intervals[left_index][0], newInterval[0]),
                                         max(right_index[1], newInterval[1])] + intervals[right_index:]
