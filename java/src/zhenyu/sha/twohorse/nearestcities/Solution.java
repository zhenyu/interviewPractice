package zhenyu.sha.twohorse.nearestcities;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
        /**
         * @param x: an array of integers, the x coordinates of each city[i]
         * @param y: an array of integers, the y coordinates of each city[i]
         * @param c: an array of strings that represent the names of each city[i]
         * @param q: an array of strings that represent the names of query locations
         * @return: the closest city for each query
         */
        public String[] nearestNeighbor(int[] x, int[] y, String[] c, String[] q) {
            List<String> result = new LinkedList<>();
            // write your code here
            Map<String, int[]> citiesCor = new HashMap<>();
            Map<Integer, TreeMap<Integer, String>> xList = new HashMap<>();
            Map<Integer, TreeMap<Integer, String>> yList = new HashMap<>();

            for (int i =0; i< c.length; i++) {
                citiesCor.put(c[i], new int[]{x[i], y[i]});
                TreeMap ycors = xList.getOrDefault(x[i], new TreeMap<>());
                ycors.put(y[i], c[i]);

                TreeMap xcors = yList.getOrDefault(x[i], new TreeMap<>());
                xcors.put(x[i], c[i]);

                xList.put(x[i], ycors);
            }
            for(String city : q) {
                result.add(queryNearest(city, citiesCor, xList, yList));
            }
            return result.toArray(new String[]{});

    }
    String queryNearest(String name , Map<String, int[]> citiesCor, Map<Integer, TreeMap<Integer, String>> xList, Map<Integer, TreeMap<Integer, String>> yList) {
          String result = "None";
          int min = Integer.MAX_VALUE;
          int x = citiesCor.get(name)[0];
          int y = citiesCor.get(name)[1];


          return result;
    }

    Map.Entry<Integer, String > getNearest(int target, TreeMap<Integer, String> cordinates ) {
        return null;
    }

}

