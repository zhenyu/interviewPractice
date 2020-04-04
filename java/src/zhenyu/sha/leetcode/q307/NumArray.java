package zhenyu.sha.leetcode.q307;

import java.util.Arrays;

class NumArray {

    int nums[];
    int BIT[];
    int SiZE ;
    public NumArray(int[] nums) {
        this.nums = nums;
        this.SiZE = nums.length+1;
        this.BIT = new int[SiZE];
        Arrays.fill(this.BIT, 0);
        for(int i = 0; i< nums.length; i++) {
            increase(i+1, nums[i]);
        }
    }

    public void update(int i, int val) {
        int increased = val-nums[i];
        nums[i]=val;
        increase(i+1, increased);
    }

    public int sumRange(int i, int j) {
        j+=1;
        int ret =0;
        while (i>0||j>0){
            ret += BIT[j]-BIT[i];
            i-=i&(-i);
            j-=j&(-j);
        }
        return ret;
    }
    private void increase(int i, int val ) {
        //to 1111
        while (i<SiZE) {
            BIT[i]+=val;
            i+=i&(-i);
        }
    }
    private int preSum(int i) {
        int ret = 0;
        while (i>0){
            ret+=BIT[i];
            i-=i&(-i);
        }
        return ret;
    }
    public static void main(String[] args) {
        //["NumArray","sumRange","update","sumRange"]
        //[[[1,3,5]],[0,2],[1,2],[0,2]]
        NumArray na = new NumArray(new int[]{1,3,5});
        System.out.println(na.sumRange(0,2));
    }
}
