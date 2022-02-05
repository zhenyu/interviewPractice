from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        begin = 0
        end = len(nums) - 1
        while begin + 1 < end:
            mid = begin + (end - begin) // 2
            if nums[mid] == target:
                return mid
            # first part is inceasing
            elif nums[begin] < nums[mid]:
                if nums[begin] <= target < nums[mid]:
                    end = mid - 1
                else:
                    begin = mid + 1
            # second part is increasing
            else:
                if nums[end] >= target > nums[mid]:
                    begin = mid + 1
                else:
                    end = mid - 1

        if nums[begin] == target:
            return begin
        if nums[end] == target:
            return end
        return -1


if __name__ == '__main__':
    print(Solution().search([3, 1], 1))
