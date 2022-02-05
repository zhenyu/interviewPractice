from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        begin = 0
        end = len(nums) - 1
        while begin + 1 < end:
            mid = begin + (end - begin) // 2
            if nums[mid] == target:
                return True
            # first part is increasing
            elif nums[begin] < nums[mid]:
                if nums[begin] <= target < nums[mid]:
                    end = mid - 1
                else:
                    begin = mid + 1
            # second part is increasing
            elif nums[mid] < nums[end]:
                if nums[end] >= target > nums[mid]:
                    begin = mid + 1
                else:
                    end = mid - 1
            elif nums[begin] == target:
                return True
            else:
                begin += 1
        if nums[begin] == target or nums[end] == target:
            return True
        return False


if __name__ == "__main__":
    print(Solution().search([1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], 13))
