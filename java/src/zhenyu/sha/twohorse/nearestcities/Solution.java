package zhenyu.sha.twohorse.nearestcities;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {

//give a list of cities names, x coor of cities, y corr of cities, and a city to look for,
// find nearest city of the city having same x or y coor as the city
//if nearest city ore than one, return the city name with largest alphabatical value

//put city with same x  into a hashmap x: list of cities with x coor the same [x,y, index]
//sor the list of cities in the mapper
//for each city to find its neibor
    //generate a min dianstance value
    //generate a result list
    //find the neiboring cities(left and right if exists) as potential candidates
    //binary search for cloest city
    //add left right return list
    //add all potential candidates together
    //set a mindistance value
    //build result list of indexes of cities based on mindistance
    //get the name of cities  from index and put into list then sort

    public static String nearestStone(String[] citieNames, int[] cityX, int[] cityY, String cityToFind){

        //TODO
        return null;

    }


    public static void main(String[] args) {
        String[] citieNames = new String[]{"c1","c2","c3","c4","c5"};
        int[] cityX = new int[]{0,0,1,1,2};
        int[] cityY = new int[]{0,1,0,1,2};
        System.out.print(nearestStone(citieNames, cityX, cityY, "c2"));
    }
}

