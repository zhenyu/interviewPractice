package zhenyu.sha.leetcode.q296;

import java.util.ArrayList;
import java.util.Random;

class Solution {
    Random random = new Random();
    public int minTotalDistance(int[][] grid) {
        ArrayList<Integer> x= new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for(int i=0;i<grid.length;i++) {
            for(int j=0; j<grid[i].length;j++){
                if(1==grid[i][j]) {
                    y.add(i);
                    x.add(j);
                }
            }
        }
        return midDistance(x)+midDistance(y);
    }

    private int midDistance(ArrayList<Integer> cords) {
        int mid = (cords.size()-1)/2;
        separate(cords, mid, 0, cords.size()-1);
        int ret =0;
        for(int cord:cords){
            ret+=Math.abs(cord-cords.get(mid));
        }
        return ret;
    }
    private void separate(ArrayList<Integer> cords, int index, int begin, int end) {
        System.out.println("begin="+begin+" end="+end+" index="+index);
        int oBegin = begin;
        int oEnd = end;
        int targetVal = cords.get(begin);
        if(end>begin){
            int s = begin+random.nextInt(end-begin);
            //swich the begin with random
            targetVal = cords.get(s);
            cords.set(s, cords.get(begin));
            cords.set(begin, targetVal);
        }
        while(begin<=end) {
            if (cords.get(begin)<=targetVal){
                begin++;
            } else {
                while (end>=begin&&cords.get(end)>targetVal){
                    end--;
                }
                //switch and update
                if(begin<end){
                    int temp = cords.get(begin);
                    cords.set(begin, cords.get(end));
                    cords.set(end, temp);
                    begin++;
                    end--;
                }
            }
        }
        //switch
        int temp = cords.get(begin-1);
        cords.set(begin-1, cords.get(oBegin));
        cords.set(oBegin, temp);
        if((begin-oBegin)-1==index){
            return;
        } else if((begin-oBegin)-1<index) {
            separate(cords, index-begin+oBegin, begin, oEnd);
        } else {
            separate(cords, index, oBegin, begin-2);
        }
    }

    public static void main(String[] args) {
        //[[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
        int [][]grid = new int[][]{{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(new Solution().minTotalDistance(grid));
    }
}