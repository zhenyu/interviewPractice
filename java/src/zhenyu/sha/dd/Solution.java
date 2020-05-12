package zhenyu.sha.dd;

import java.util.*;

class Dur implements Comparable<Dur> {
    Dur(int start, int end) {
        this.start = start;
        this.end = end;
    }

    int start;
    int end;

    @Override
    public int compareTo(Dur o) {
        return this.start - o.start;
    }


}

public class Solution {
    static List<Dur> getSuggestions(List<Dur> meetings, int start, int end, int dur) {
        List<Dur> result = new LinkedList<Dur>();

        meetings=mergeDur(meetings);
        int i = 0;
        while (start + dur <= end&&i<meetings.size()) {
            Dur currentDur = meetings.get(i);
            // this part can be don be search
            if (currentDur.end <= start) {
                i++;
            } else {
                if (start + dur <= currentDur.start) {
                    //append the result;
                    result.add(new Dur(start, currentDur.start));
                }
                start = currentDur.end;
                i++;
            }
        }

        if(end-start>=dur) {
            result.add(new Dur(start, end));
        }
        return result;
    }

    static List<Dur> mergeDur(List<Dur> meetings) {
        if (meetings.size() <= 1)
            return meetings;
        Collections.sort(meetings);
        List<Dur> result = new ArrayList<>();
        Dur cur = meetings.get(0);
        for (int i = 1; i < meetings.size(); i++) {
            Dur next = meetings.get(i);
            if (next.start <= cur.end) {
                //avoid full coverage
                if(next.end>cur.end) {
                    cur.end = next.end;
                }
            } else {
                result.add(cur);
                cur = next;
            }
        }
        result.add(cur);
        return result;
    }
    public static void main(String[] args) {
        //[3,20], [-2, 0], [0,2], [16,17], [19,23], [30,40], [27, 33]
        // start -5;
        //end 27, dur2
        ArrayList<Dur> meetings = new ArrayList<>();
        meetings.add(new Dur(3, 20));
        meetings.add(new Dur(-2, 0));
        meetings.add(new Dur(0, 2));
        meetings.add(new Dur(16, 17));
        meetings.add(new Dur(19, 23));
        meetings.add(new Dur(30, 40));
        meetings.add(new Dur(27, 33));
        for (Dur suggest : getSuggestions(meetings, -5, 27, 2)) {
            System.out.println("start =" + suggest.start + " end=" + suggest.end);
        }
    }
}
