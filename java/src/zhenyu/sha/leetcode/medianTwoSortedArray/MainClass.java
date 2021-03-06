package zhenyu.sha.leetcode.medianTwoSortedArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if((length & 1) == 1)
            return kth(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, length/2);

        double first = kth(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, length/2);
        double second = kth(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, length/2+1);
        return (first+second)/2;

    }
    private double kth(int[] nums1, int begin1, int end1, int[] nums2, int begin2, int end2, int k) {
        if(end1-begin1>end2-begin2) {
            return kth(nums2, begin2, end2, nums1, begin1, end1, k);
        }
        int half = (k-1)/2;
        int index = higherBound(nums1, begin1, end1, nums2[begin2+half]);
        int numLower = index-begin1+ 1 + half+1;
        if(numLower==k)
            return nums2[begin2+half];
        else if (numLower>k)
            return kth(nums1, begin1, index, nums2, begin2, begin2+half-1, k);
        else
            return kth(nums1, index+1, end1, nums2, begin2+half+1, end2, k-numLower);
    }

    private int higherBound(int [] nums, int begin, int end, int target) {
        int oldbegin = begin;
        while(begin<end){
            int mid = begin + (end-begin+1)/2;
            if(nums[mid]<=target) {
                begin = mid;
            } else
                end = mid-1;
        }
        if(end<begin||nums[end]<=target)
            return end;
        return oldbegin-1;
    }
}



public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            double ret = new Solution().findMedianSortedArrays(nums1, nums2);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }
}