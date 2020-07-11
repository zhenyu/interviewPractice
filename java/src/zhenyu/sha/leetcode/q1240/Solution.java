package zhenyu.sha.leetcode.q1240;

class Solution {
    int count ;
    int h;
    public int tilingRectangle(int n, int m) {
        int count = m*n;
        if(n<m){
            int temp  =n;
            n=m;
            m= temp;
        }
        h = m;
        int [] fill = new int[n];
        dfs(fill, 0);
        return count;
    }

    private void dfs(int [] fill, int curCount) {
        if(curCount>=count){
            return;
        }
        int minIndex = fill.length-1;
        // check the height;
        for (int i = fill.length-2;i>=0;i--){
            if(fill[i]<=fill[minIndex]){
                minIndex =i;
            }
        }
        if(fill[minIndex]==h){
            count = curCount;
            return;
        }
        // try the max width
        int w =1;
        int curH = fill[minIndex];
        while (curH==fill[minIndex+w]&&curH+w<=h){
            w++;
        }
        for(int i=w;i>0;i--) {
            // fill the width
            for(int j = minIndex; j<minIndex+i;j++) {
                fill[j]+=i;
            }
            dfs(fill, curCount+1);
            // remove current case
            // fill the width
            for(int j = minIndex; j<minIndex+i;j++) {
                fill[j]-=i;
            }
        }
    }

}