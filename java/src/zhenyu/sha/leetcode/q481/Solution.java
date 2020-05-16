package zhenyu.sha.leetcode.q481;
import java.util.*;
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {

        int colCount = 0;
        int rowCount = 0;
        int count=0;
        int i =0;
        while(rowCount<rows) {
            if(sentence[i].length()>cols){
                return 0;
            }
            if(colCount==0) {
                colCount=sentence[i].length()+1;
                i++;

            } else {
                colCount+=sentence[i].length()+1;
                if(colCount-1>=cols) {
                    rowCount++;
                    if(colCount-1==cols){
                        i++;
                    }
                    colCount=0;

                } else  {
                    i++;
                }
            }
            if(i==sentence.length){
                count++;
                if(colCount==0){
                    count=rows/rowCount*(count);
                    rowCount=rowCount*(rows/rowCount);
                }
                i=0;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        //["a", "bcd", "e"]
        //3
        //6
        System.out.println(new Solution().wordsTyping(new String[]{"f", "p", "a"}, 8, 7));
    }
}
