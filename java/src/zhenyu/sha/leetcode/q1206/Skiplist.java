package zhenyu.sha.leetcode.q1206;
import java.util.*;
//This is from internet , will reimplement by myself
class Skiplist {
    private SkipListEntry head = new SkipListEntry(Integer.MIN_VALUE);
    private SkipListEntry tail = new SkipListEntry(Integer.MAX_VALUE);
    private int n;
    private int h;
    private Random random = new Random();

    public Skiplist() {
        head.right = tail;
        tail.left = head;
    }

    public boolean search(int target) {
        SkipListEntry entry = floorEntry(target);
        return entry.val == target;
    }

    public void add(int num) {
        SkipListEntry p = floorEntry(num);
        SkipListEntry q = new SkipListEntry(num);
        link(p, q);
        int i = 0;
        while (random.nextDouble() < 0.5) {
            if (i == h) addEmptyRow();
            while (p.up == null) p = p.left;
            p = p.up;
            SkipListEntry e = new SkipListEntry(num);
            link(p, e);
            e.down = q;
            q.up = e;
            q = e;
            i++;
        }
        n++;
    }

    public boolean erase(int num) {
        SkipListEntry entry = floorEntry(num);
        if (entry.val != num) return false;
        do {
            entry.left.right = entry.right;
            entry.right.left = entry.left;
            entry = entry.up;
        } while (entry != null);
        return true;
    }

    private SkipListEntry floorEntry(int target) {
        SkipListEntry entry = head;
        while (true) {
            while (entry.right.val <= target) entry = entry.right;
            if (entry.down == null) break;
            entry = entry.down;
        }
        return entry;
    }

    private void addEmptyRow() {
        SkipListEntry a = new SkipListEntry(Integer.MIN_VALUE);
        SkipListEntry b = new SkipListEntry(Integer.MAX_VALUE);
        a.down = head;
        head.up = a;
        b.down = tail;
        tail.up = b;
        a.right = b;
        b.left = a;
        head = a;
        tail = b;
        h++;
    }

    private void link(SkipListEntry p, SkipListEntry q) {
        p.right.left = q;
        q.right = p.right;
        p.right = q;
        q.left = p;
    }

    class SkipListEntry {
        private Integer val;
        private SkipListEntry left;
        private SkipListEntry right;
        private SkipListEntry up;
        private SkipListEntry down;
        SkipListEntry(Integer val) {
            this.val = val;
        }
    }
}