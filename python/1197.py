from collections import deque
class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        offsets =[(-2,1),(-1,2),(1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1)]
        visited = set()
        q = deque()
        #x, y, z and offsets
        q.append((0,0))
        visited.add((0,0))
        steps = 0
        while q:
            curr_level_cnt = len(q)
            for i in range(curr_level_cnt):
                current = q.popleft()
                if current == (x,y) :
                    return steps
                for offset in offsets:
                    if (current[0]+offset[0],current[1]+offset[1]) not in visited:
                        visited.add((current[0]+offset[0],current[1]+offset[1]))
                        q.append((current[0]+offset[0],current[1]+offset[1]))
            steps+=1        
        return -1
if __name__ == '__main__':
    steps = Solution().minKnightMoves(2,112)
    print(steps)