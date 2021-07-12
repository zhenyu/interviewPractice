package zhenyu.sha.leetcode.q321;

/**
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 *
 * Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits from the same array must be preserved.
 *
 * Return an array of the k digits representing the answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
 * Output: [9,8,6,5,3]
 * Example 2:
 *
 * Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
 * Output: [6,7,6,0,4]
 * Example 3:
 *
 * Input: nums1 = [3,9], nums2 = [8,9], k = 3
 * Output: [9,8,9]
 *
 *
 * Constraints:
 *
 * m == nums1.length
 * n == nums2.length
 * 1 <= m, n <= 500
 * 0 <= nums1[i], nums2[i] <= 9
 * 1 <= k <= m + n
 */
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int [] ret = new int[k];
        int i=0;
        int j=0;
        for(;k>0;k--){
            int nextI = i;
            int nextJ=j;
            while(nextI< nums1.length&&nextJ< nums2.length&&nums1[nextI]==nums2[nextJ]){
                nextJ++;
                nextI++;
            }
            if(nextI< nums1.length&&nextJ< nums2.length){
                if()
            } else {

            }
        }
    }
}
