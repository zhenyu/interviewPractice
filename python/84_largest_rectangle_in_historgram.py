from typing import List


class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        stack = []
        heights.insert(0, 0)
        heights.append(0)
        result = 0
        for index in range(0, len(heights)):
            while stack and heights[stack[-1]] >= heights[index]:
                # pop
                prev_index = stack.pop()
                height = heights[prev_index]
                width = index - stack[-1] -1 if stack else 0
                result = max(result, height * width)
            stack.append(index)

        return result


if __name__ == '__main__':
    print(Solution().largestRectangleArea([2, 1, 5, 6, 2, 3]))
