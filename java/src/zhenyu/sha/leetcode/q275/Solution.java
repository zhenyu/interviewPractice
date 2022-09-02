package zhenyu.sha.leetcode.q275;
import java.util.*;

/**
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 *
 * 根据维基百科上 h 指数的定义：
 * h 代表“高引用次数”，一名科研人员的 h指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 n - h 篇论文每篇被引用次数 不超过 h 次。
 *
 * 如果 h 有多种可能的值，h 指数 是其中最大的那个。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/h-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public int hIndex(int[] citations) {
        if (isNotLargerThanH(citations, citations.length)){
            return citations.length;
        }
        int begin = 0;
        int end = citations.length;
        while (begin+1<end){
            int mid = begin+(end-begin)/2;
            if (isNotLargerThanH(citations, mid)){
                begin =mid;
            } else {
                end = mid;
            }
        }
        return begin;
    }
    public static void main (String[] args) {
        System.out.println(new Solution().hIndex(new int[]{0,1,3,5,6}));
    }
    private boolean isNotLargerThanH(int[] citations, int c) {
        if(citations[0]>=c){
            return true;
        }
        if (citations[citations.length-1]<c){
            return  false;
        }
        int begin = 0;
        int end = citations.length-1;
        while (begin+1<end){
            int mid = begin+(end-begin)/2;
            if(citations[mid]<c){
                begin = mid;
            } else {
                end = mid;
            }
        }
        return (citations.length-(begin+1)) >= c;

    }

}
