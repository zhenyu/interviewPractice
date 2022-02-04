from typing import List
from operator import itemgetter


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        result = []
        intervals.sort(key=lambda x: x[0])
        prev = None
        while intervals:
            interval = intervals.pop(0)
            if not prev:
                prev = interval
            elif prev[1] < interval[0]:
                result.append(prev)
                prev = interval
            else:
                prev = [prev[0], max(prev[1],interval[1])]

        result.append(prev)
        return result


if __name__ == '__main__':
    intervals = [[2, 6], [1, 3], [8, 10], [15, 18]]
    for i in Solution().merge(intervals):
        print(i)
