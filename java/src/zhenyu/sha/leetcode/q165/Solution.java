package zhenyu.sha.leetcode.q165;

class Solution {
    int getInt (String intString) {
        int ret=0;
        int base =1;
        for(int i = intString.length()-1;i>=0;i--){
            ret+=base*(intString.charAt(i)-'0');
            base *= 10;
        }
        return ret;
    }
    public int compareVersion(String version1, String version2) {
        String[] revisions1 = version1.split("\\.");
        String[] revisions2 = version2.split("\\.");
        int i =0;
        while (i<revisions1.length||i<revisions2.length){
            int ret = Integer.compare(getInt(i<revisions1.length? revisions1[i]:"0"),
                    getInt(i<revisions2.length? revisions2[i]:"0"));
            if(ret!=0){
                return ret;
            }
            i++;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().compareVersion("1.0", "1.10"));
    }
}