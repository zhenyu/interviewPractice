package zhenyu.sha.hrt.diamondciphe;

public class Solution {
    public void encrpt(char[][] m) {
        int n = m.length/2;
        for(int i=0; i < n;i+=2) {
           for(int j =0; j<n; j++) {
               swap(m, i, j, i+1,j);
           }
        }
        for(int i=0; i < n;i+=2) {
            for(int j =i; j<n; j++) {
                swap(m, i, j, j*2,i);
                swap(m, i+1, );
            }
        }
        // rotate
    }
    void swap(char[][] m , int x1, int y1, int x2, int y2) {
        char temp = m[x1][y1];
        m[x1][y1]=m[x2][y2];
        m[x2][y2]= temp;
    }
}
