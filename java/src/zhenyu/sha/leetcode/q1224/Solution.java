package zhenyu.sha.leetcode.q1224;
/*
class Solution:
    def maxEqualFreq(self, nums: List[int]) -> int:
        num_freq = defaultdict(int)
        freq_freq = defaultdict(int)
        res = 1                                         #最短的就是1个数字的情况
        species = 0                                     #数字种类数
        max_freq = 0                                    #最大的频率
        for i, x in enumerate(nums):
            if num_freq[x] == 0:
                species += 1
            num_freq[x] += 1
            max_freq = max(max_freq, num_freq[x])
            freq_freq[num_freq[x]] += 1
            freq_freq[num_freq[x] - 1] -= 1             #很容易忘记

            if species == i + 1 or species == 1:        # 123456 111111
                res = i + 1
            if freq_freq[max_freq] == species - 1 and freq_freq[1] == 1:    #111 222 333 4
                res = i + 1
            if freq_freq[max_freq] == 1 and freq_freq[max_freq - 1] == species - 1: #111 222 333 4444
                res = i + 1
        return res

作者：XingHe_XingHe
链接：https://leetcode.cn/problems/maximum-equal-frequency/solution/cpython3-fen-4chong-qing-kuang-xiang-qin-ucsl/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
import java.util.*;
class Solution {
    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> numFreq = new HashMap<>();
        Map<Integer, Integer> freqFreq = new HashMap<>();
        int result = 0;
        int species = 0;
        int maxFreq  = 0;
        for(int i=0; i<nums.length;i++) {
            int current = nums[i];
            if(numFreq.get(current)==null) {
                species+=1;
            }
            int currentFreq = numFreq.getOrDefault(current, 0)+1;
            numFreq.put(current, currentFreq);
            maxFreq= Math.max(maxFreq, currentFreq);
            freqFreq.put(currentFreq, freqFreq.getOrDefault(currentFreq,0)+1);
            if(currentFreq>1) {
                freqFreq.put(currentFreq-1, freqFreq.get(currentFreq-1)-1);
            }

            if(species==i+1||species==1) {
                result=i+1;
            }
            if(freqFreq.get(maxFreq)==species - 1 &&freqFreq.getOrDefault(1,0)==1) {
                result = i+1;
            }
            if(freqFreq.get(maxFreq)==1&&freqFreq.getOrDefault(maxFreq-1,0)==species-1) {
                result = i+1;
            }

        }
        return result;
    }
    public static void main(String[] argv){
        System.out.println(new Solution().maxEqualFreq(new int[]{1,1,1,2,2,2,3,3,3,4,4,4,4}));
    }
}