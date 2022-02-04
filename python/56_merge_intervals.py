from  typing import List
import heapq


class Node:
    def __init__(self, event_type: int, val: int):
        self.event_type = event_type
        self.val = val

    def __lt__(self, other):
        if self.val == other.val:
            return self.event_type < other.event_type
        return self.val < other.val


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        # init the scan line
        heap = []
        for interval in intervals:
            heapq.heappush(heap, Node(0, interval[0]))
            heapq.heappush(heap, Node(1, interval[1]))

        result = []
        count = 0
        start = -1;

        while heap:
            event = heapq.heappop(heap)
            # begin the interval
            if not event.event_type:
                if not count:
                    start = event.val
                count += 1
                # end the interval
            else:
                count -= 1
                if not count:
                    result.append([start, event.val])

        return result


if __name__ == '__main__':
    intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
    for interval in Solution().merge(intervals):
        print(interval)
