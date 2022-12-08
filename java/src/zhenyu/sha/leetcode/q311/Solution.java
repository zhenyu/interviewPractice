package zhenyu.sha.leetcode.q311;
import java.util.*;
class SparseMatrix {
    int m;
    int n;
    LinkedList<ArrayList<int[]>> rowValues;
    ArrayList<Integer> rowIndexes;

    public SparseMatrix(int i, int j) {
        m =i;
        n =j;
        rowValues = new LinkedList<>();
        rowIndexes = new ArrayList<>();
    }
    public void append(int i, int j, int value) {
        int curRow = rowIndexes.isEmpty()?-1: rowIndexes.get(rowIndexes.size()-1);
        ArrayList<int[]> row = rowValues.isEmpty()?null:rowValues.getLast();
        if(i!=curRow){
            curRow=i;
            rowIndexes.add(curRow);
            row = new ArrayList<>();
            rowValues.add(row);
        }
        row.add(new int[]{j, value});
    }
    int vectorMult(List<int[]> v1, List<int[]> v2 ){
        int i=0;
        int j=0;
        int ret =0;
        while (i<v1.size()&&j<v2.size()){
            int v1Index = v1.get(i)[0];
            int v2Index = v2.get(j)[0];
            if(v1Index==v2Index){
                ret+=v1.get(i)[1]*v2.get(j)[1];
                i++;
                j++;
            } else if (v1Index<v2Index){
                i++;
            } else {
                j++;
            }
        }
        return ret;
    }
    SparseMatrix multipleAfterRotate(SparseMatrix other) {
        SparseMatrix result = new SparseMatrix(this.m, other.m);
        int i =0;
        for(List<int[]> row : rowValues){
            int rowIndex = rowIndexes.get(i);
            int otherI =0;
            for (List<int[]> col: other.rowValues){
                int colIndex = other.rowIndexes.get(otherI);
                int val = vectorMult(row, col);
                if (val!=0){
                    result.append(rowIndex, colIndex, val);
                }
                otherI++;
            }
            i++;
        }
        return result;
    }
    int[][] toDense(){
        int[][] result = new int[m][n];
        int i=0;
        for(List<int[]> row : rowValues){
            int rowIndex = rowIndexes.get(i);
            for(int[] value: row ){
                result[rowIndex][value[0]]=value[1];
            }
            i++;
        }
        return result;
    }
}
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        SparseMatrix sm1 = new SparseMatrix(mat1.length, mat1[0].length);
        for (int i =0;i<mat1.length;i++) {
            for(int j=0;j<mat1[i].length;j++){
                sm1.append(i,j, mat1[i][j]);
            }
        }
        SparseMatrix sm2 = new SparseMatrix(mat2[0].length, mat2.length);
        for(int j=0;j<mat2[0].length;j++){
            for(int i=0; i< mat2.length;i++) {
                sm2.append(j, i, mat2[i][j]);
            }
        }
        SparseMatrix result = sm1.multipleAfterRotate(sm2);
        return result.toDense();
    }
}