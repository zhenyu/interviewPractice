package zhenyu.sha.leetcode.q388;

class Solution {
    public int lengthLongestPath(String input) {
        int ret =0;
        if(null!=input&&input.length()>0) {
            int[] depths = new int[input.length()];
            int start =0;
            char[] inputs = input.toCharArray();
            while(true){
                int [] next = getDepAndPath(inputs, start);
                if(null==next){
                    break;
                }
                int nDep =next[0];
                int pathEnd = next[1];
                int pathLen = next[2];
                boolean isFile=next[3]==1;
                //cum
                depths[nDep]=(nDep>0?depths[nDep-1]:0)+pathLen;
                if(isFile) {
                    ret = Math.max(ret, depths[nDep]);
                }
                start=pathEnd;
            }
        }
        return ret;
    }
    int[] getDepAndPath(char[] input, int start) {
        if (start > input.length - 1)
            return null;
        int dep = 0;
        int end = start;
        int len = 0;
        boolean dotFound =false;
        while (end < input.length) {
            char c = input[end];
            end++;
            if (c == '\t') {
                dep++;
            } else if (c == '\n') {
                break;
            } else {
                if(c=='.') {
                    dotFound=true;
                }
                len++;
            }
        }
        if(!dotFound) {
            len++;
        }
        return new int[]{dep, end, len, dotFound?1:0};
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthLongestPath("a"));
    }
}
