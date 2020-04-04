package zhenyu.sha.leetcode.q307;

class NumArray {
    static class STNode {
        int begin;
        int end;
        int val;
        STNode left;
        STNode right;
        public STNode(int begin, int end) {
            this.begin = begin;
            this.end = end;
            if(begin>=end-1)
                return;
            int mid = begin+ (end-begin)/2;
            if(begin<mid){
                left = new STNode(begin, mid);
            }
            if(mid<end) {
                right = new STNode(mid, end);
            }
        }
        void update(int i, int j) {
            int mid = begin+ (end-begin)/2;
            if(i>=begin&&i<mid) {
                val+=j;
                if (null!=left)
                    left.update(i,j);
            }
            if(i>=mid&&i<end) {
                val+=j;
                if(null!=right)
                    right.update(i,j);
            }
        }
        public int sumRange(int i, int j) {
            if(i==begin&&end==j)
                return val;
            int mid = begin+ (end-begin)/2;
            int ret = 0;
            if(i>=begin) {
                if(i<mid) {
                    ret += left.sumRange(i,Math.min(mid,j));
                }
                if(j>mid&&j<=end) {
                    ret+=right.sumRange(Math.max(mid, i), j);
                }
            }
            return ret;
        }
    }
    STNode root;
    int nums[];
    public NumArray(int[] nums) {
        this.nums = nums;
        root = new STNode(0, nums.length);
        for(int i =0 ; i<nums.length;i++) {
            root.update(i, nums[i]);
        }
    }
    public void update(int i, int val) {
        root.update(i, val-nums[i]);
        nums[i]=val;
    }

    public int sumRange(int i, int j) {
        return root.sumRange(i,j+1);
    }
    public static void main(String[]args) {
        //["NumArray","sumRange","update","sumRange","sumRange","update","update","sumRange","sumRange","update","update"]
        //[[[-28,-39,53,65,11,-56,-65,-39,-43,97]],[5,6],[9,27],[2,3],[6,7],[1,-82],[3,-72],[3,7],[1,8],[5,13],[4,-67]]
        int[] nums = new int[]{-28,-39,53,65,11,-56,-65,-39,-43,97};
        NumArray nA =new NumArray(nums);
        System.out.println(nA.sumRange(2,3));

    }
}
