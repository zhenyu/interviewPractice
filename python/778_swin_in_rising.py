import heapq
from typing import List


class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        heap = []
        x = len(grid)
        y = len(grid[0])
        directs = [(-1, 0), (0, -1), (1, 0), (0, 1)]
        visited = [[False for _ in range(y)] for _ in range(x)]
        time =0
        heapq.heappush(heap, (grid[0][0], 0, 0))
        visited[0][0] = True
        while len(heap):
            # pop
            t, _x, _y = heapq.heappop(heap)
            time = max(time, t)
            if _x == x - 1 and _y == y - 1:
                return time
            for direct in directs:
                next_x = _x + direct[0]
                next_y = _y + direct[1]
                if 0 <= next_x < x and 0 <= next_y < y and not visited[next_x][next_y]:
                    visited[next_x][next_y] = True;
                    heapq.heappush(heap, (grid[next_x][next_y], next_x, next_y))

        return -1


if __name__ == "__main__":
    grid = [[0, 1, 2, 3, 4], [24, 23, 22, 21, 5], [12, 13, 14, 15, 16], [11, 17, 18, 19, 20], [10, 9, 8, 7, 6]]
    print(Solution().swimInWater(grid))
