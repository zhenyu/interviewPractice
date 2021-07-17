package zhenyu.sha.leetcode.q23;

import java.util.PriorityQueue;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
 class NodeWrapper implements Comparable<NodeWrapper>{
    ListNode current;
     NodeWrapper(ListNode node) {
         current = node;
     }
     @Override
     public int compareTo(NodeWrapper o) {
         return Integer.compare(this.current.val, o.current.val);
     }
 }
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode tail = head;
        PriorityQueue<NodeWrapper> que = new PriorityQueue<>();
        for(ListNode node : lists) {
            que.add(new NodeWrapper(node));
        }
        while (!que.isEmpty()){
            ListNode cur = que.poll().current;
            if (cur.next!=null) {
                que.add(new NodeWrapper(cur.next));
            }
            cur.next= null;
            tail.next=cur;
            tail = cur;
        }
        return head.next;
    }
}
