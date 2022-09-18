package zhenyu.sha.leetcode.q1675;
import java.util.Comparator;
import java.util.PriorityQueue;
class Solution {
    public int minimumDeviation(int[] nums) {
        if (nums==null||nums.length<2){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int ret = Integer.MAX_VALUE;
        PriorityQueue<Integer> que = new PriorityQueue<>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for( int num : nums) {
            if (num%2==1){
                num = num*2;
            }
            min = Math.min(min, num);
            que.add(num);

        }
        while(true){
            int num = que.poll();
            ret = Math.min(ret, num-min);
            if(num%2==1){
                return ret;
            }
            num = num /2;
            min = Math.min(min, num);
            que.add(num);
        }
    }
    public static void main(String[] args) {
        System.out.println(new Solution().minimumDeviation(new int[]{3,5}));
    }
}