package zhenyu.sha.leetcode.q939;


import java.util.*;

class LinePoints implements Comparable<LinePoints>{
    LinePoints(int line ){
        this.line=line;
        points = new HashSet<>();
    }
    int line;
    Set<Integer> points;

    @Override
    public int compareTo(LinePoints o) {
        return this.line-o.line;
    }
}
class Solution {
    public int minAreaRect(int[][] points) {
        int ret = Integer.MAX_VALUE;
        Map<Integer, LinePoints> linePointsMap = new HashMap<>();
        for(int[] point : points) {
            int x= point[0];
            int y= point[1];
            LinePoints linePoints = linePointsMap.getOrDefault(y, new LinePoints(y));
            linePoints.points.add(x);
            linePointsMap.put(y, linePoints);
        }
        //sort the linePoints
        ArrayList<LinePoints> lines = new ArrayList<>(linePointsMap.values());
        Collections.sort(lines);
        for(int i=0;i<lines.size();i++) {
            int h=lines.get(i).line;
            //sort the points
            ArrayList<Integer> curPoints = new ArrayList<>(lines.get(i).points);
            Collections.sort(curPoints);
            for(int j=0;j<curPoints.size()-1;j++){
                int curPoint = curPoints.get(j);
                for(int l= i+1;l<lines.size();l++) {
                    if(lines.get(l).points.contains(curPoint)){
                        for(int k=j+1;k<curPoints.size();k++){
                            int nextPoint = curPoints.get(k);

                            if(lines.get(l).points.contains(nextPoint)){
                                int hd=lines.get(l).line-h;
                                ret = Math.min(hd*(nextPoint-curPoint), ret);
                            }

                        }
                    }
                }

            }

        }
        return ret==Integer.MAX_VALUE?0:ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minAreaRect(new int[][]{{0,1},{0,0},{3,1},{1,3},{4,3},{1,0},{2,4}}));
    }
}
