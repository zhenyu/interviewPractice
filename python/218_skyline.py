import heapq
from sortedcontainers import SortedList
from typing import List

class Event:
    def __init__(self, type: int, index: int, val: int):
        self.type = type
        self.index = index
        self.val = val

    def __lt__(self, other):
        if self.index == other.index:
            if self.type == other.type:
                if self.type:
                    return self.val < other.val
                else:
                    return self.val > other.val
            return self.type < other.type
        return self.index < other.index


class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        heap = []
        result = []
        prev_height = 0
        sorted_list = SortedList()
        for building in buildings:
            heapq.heappush(heap, Event(0, building[0], building[2]))
            heapq.heappush(heap, Event(1, building[1], building[2]))
        while heap:
            event = heapq.heappop(heap)
            if event.type:
                sorted_list.remove(event.val)
            else:
                sorted_list.add(event.val)
            height = sorted_list[-1] if sorted_list else 0
            if height != prev_height:
                result.append([event.index, height])
                prev_height = height
        return result

if __name__ == '__main__':
    print(Solution().getSkyline([[0,2,3],[2,4,3],[4,6,3]]))