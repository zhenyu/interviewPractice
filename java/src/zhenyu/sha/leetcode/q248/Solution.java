package zhenyu.sha.leetcode.q248;

import java.util.*;
class Solution {
    Map<Integer, Integer> counter;
    int[] possibleOne = new int[]{8,1,0};
    Map<Integer, Integer> leadingMirrors;
    Solution(){
        counter = new HashMap<>();
        counter.put(0,1);//this is special
        counter.put(1,3); //0, 1, 8
        counter.put(2,5); // 11, 88, 69, 96 00
        leadingMirrors = new HashMap<>();
        leadingMirrors.put(1,1);
        leadingMirrors.put(6,9);
        leadingMirrors.put(8,8);
        leadingMirrors.put(9,6);
        leadingMirrors.put(0,0);
    }
    public int strobogrammaticInRange(String low, String high) {
        int len1 = low.length();
        int len2 = high.length();
        if(len2<len1){
            return 0;
        }
        int count = 0;
        count+= lagerCounter(len1, low.toCharArray(), 0, len1-1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }, false);
        count+= lagerCounter(len2, high.toCharArray(), 0,len2-1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }, false);
        if(len1==len2) {
            count-=counter(len1);
        } else {
            for(int l=len1+1; l<len2; l++) {
                count+=counter(l);
            }
        }
        return count;
    }
    int lagerCounter(int l, char[] target, int hIndex, int lIndex, Comparator<Integer> comparator, boolean isRestrict){
        int count =0;
        int highDigit= target[hIndex]-'0';
        int lowDigit = target[lIndex]-'0';
        if(l==0) {
            if(!isRestrict) {
                count =1;
            }
        }
        else if(l==1){
            for(int i: possibleOne) {
                if(comparator.compare(i, lowDigit)>0) {
                    count++;
                } else if (!isRestrict&&comparator.compare(i, lowDigit)==0){
                    count++;
                }
            }
        } else {
           // check from leading
           for(Map.Entry<Integer, Integer> entry: leadingMirrors.entrySet()){
               int leadDigit = entry.getKey();
               int mirror = entry.getValue();
               if(leadDigit==0&&l==target.length) {
                   continue;
               }
               if(comparator.compare(leadDigit, highDigit)>0){
                   count+=inerCounter(l-2);
               } else if(comparator.compare(leadDigit,highDigit)==0){
                   //大于
                   if(comparator.compare(mirror,lowDigit)>0){
                       count+=lagerCounter(l-2, target, hIndex+1, lIndex-1, comparator, false);
                   } else if(comparator.compare(mirror,lowDigit)==0) {
                       // 时候严格要看外边的要求
                       count+= lagerCounter(l-2, target,hIndex+1, lIndex-1, comparator, isRestrict);
                   } else {
                       count+=lagerCounter(l-2, target, hIndex+1, lIndex-1, comparator, true);
                   }
               }
           }
        }
        return count;
    }
    int counter(int l){
        if(l==1) {
            return 3;
        }
        return 4*inerCounter(l-2);
    }
    int inerCounter(int l) {
        Integer ret = counter.get(l);
        if(null==ret){
            ret=5*inerCounter(l-2);//0, 1, 6, 8, 9
            counter.put(l, ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().strobogrammaticInRange("9695", "9697"));
    }
}
