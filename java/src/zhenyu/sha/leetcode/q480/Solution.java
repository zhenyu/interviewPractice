package zhenyu.sha.leetcode.q480;

import java.util.TreeSet;

class Node implements Comparable<Node>{
    int val;
    int index;
    Node(int val, int index) {
        this.index=index;
        this.val=val;
    }
    public int compareTo(Node o){
        long ret = (long)(this.val)-(long)o.val;
        if(ret==0){
            ret=this.index-o.index;
        }
        return ret==0?0:(ret<0?-1:1);
    }
}
class Solution {

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ret = new double[nums.length-k+1];
        TreeSet<Node> firstHalf=new TreeSet<>();
        int firstSize = (k+1)/2;
        TreeSet<Node> secHalf =new TreeSet<>();
        int secondSize = k/2;
        for(int i=0;i<nums.length;i++){

            if(firstHalf.size()<firstSize||(nums[i]<firstHalf.last().val)){
                firstHalf.add(new Node(nums[i], i));
            } else {
                secHalf.add(new Node(nums[i], i));
            }
            if(i-k>=0){
                Node out = new Node(nums[i-k], i-k);
                firstHalf.remove(out);
                secHalf.remove(out);
            }

            while (firstHalf.size()>firstSize){
                secHalf.add(firstHalf.pollLast());
            }
            while (secHalf.size()>secondSize){
                firstHalf.add(secHalf.pollFirst());
            }
            if(firstHalf.size()==firstSize&&secHalf.size()==secondSize){
                //first result;
                if(k%2==0){
                    ret[i+1-k]= ((double)(firstHalf.last().val)+(double)secHalf.first().val)/2;
                } else {
                    ret[i+1-k]= firstHalf.last().val;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println((long)Integer.MIN_VALUE-Integer.MAX_VALUE);
        System.out.println((long)Integer.MAX_VALUE-Integer.MIN_VALUE);
        //{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648}
        System.out.println(new Solution().medianSlidingWindow(new int[]{2147483647,2147483647,2147483647,-2147483648,2147483647},3));
    }
}