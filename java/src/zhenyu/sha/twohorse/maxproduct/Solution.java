package zhenyu.sha.twohorse.maxproduct;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


// 一个朋友关系和company，找最大的set，类似于island那题。输入是{[1,2, 1], [2,3,1]}前两个是朋友id，最后一个是companyID，meaning 在company1下，1和2是朋友，2和3也是朋友，所以这个集合就是1，2，3在company1下。同一个人可以在多个company下，求最大的这个集合
//  白人大哥，input是一组数字每组三个数，分别代表friendId, friendId, companyId，问你max product of two friendIds in the same company with longest graph. 需要主语的是如果friend1和friend2connect然后friend2和friend3 connect，friend1也会算是friend3的friend。

//can we have more than one set that have greatest number of friends?
//group friends by company
//max friend group size
//maintain a list of list (max friends set)
//create a djs for each company
//friends pair list of list
//hashmap friendAndCenter: centerFriend
//hashmap allfriendInACenter: all friends
//a function to connect friend
//update the center of one p to center of another p
//move all friends of one group to another
//a function to find center friend of a person

public class Solution {

    public static int maxProductFriendsGroup(List<List<Integer>> friendsInACompany){
        // build graph
        Map<Integer, Map<Integer, List<Integer>>> companyFriends = new HashMap<>();
        for(List<Integer> friends: friendsInACompany) {
            int companyId = friends.get(2);
            Map<Integer, List<Integer>> friendGraph = companyFriends.getOrDefault(companyId, new HashMap<>());
            makeFriends(friends.get(0), friends.get(1), friendGraph);
            makeFriends(friends.get(1), friends.get(0), friendGraph);
            companyFriends.put(companyId, friendGraph);

        }
        int maxProduct = Integer.MIN_VALUE;
        for( Map<Integer, List<Integer>> friendGraph : companyFriends.values()) {
            maxProduct = Math.max(maxProduct,getMaxProduct(friendGraph));
        }
        return  maxProduct;
    }
    static int getMaxProduct(Map<Integer, List<Integer>> friendGraph) {
        int max = Integer.MIN_VALUE;
        Set<Integer> group = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for(int person: friendGraph.keySet()) {
            group.clear();
            dfs(person, group, visited, friendGraph);
            max = Math.max(max, group.size());
        }
        return max;
    }
    static void dfs(int root, Set<Integer> group, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if(visited.contains(root)) {
            return;
        }
        visited.add(root);
        group.add(root);
        for( int neigh:  graph.get(root)) {
            dfs(neigh, group, visited, graph);
        }
    }
    static void makeFriends(int friend1, int friend2, Map<Integer, List<Integer>> friendGraph) {
        List<Integer> friendList = friendGraph.getOrDefault(friend1, new LinkedList<>());
        friendList.add(friend2);
        friendGraph.put(friend1,friendList);
    }
    public static void main(String[] args) {
        List<Integer> fp1 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp1.add(1);
        fp1.add(2);
        fp1.add(1);
        List<Integer> fp2 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp2.add(2);
        fp2.add(7);
        fp2.add(1);
        List<Integer> fp3 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp3.add(4);
        fp3.add(1);
        fp3.add(1);
        List<Integer> fp4 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp4.add(1);
        fp4.add(4);
        fp4.add(2);
        List<Integer> fp5 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp5.add(3);
        fp5.add(4);
        fp5.add(2);
        List<List<Integer>> friends = new ArrayList<>();
        friends.add(fp1);
        friends.add(fp2);
        friends.add(fp3);
        friends.add(fp4);
        friends.add(fp5);
        System.out.print(maxProductFriendsGroup(friends));
    }
}
