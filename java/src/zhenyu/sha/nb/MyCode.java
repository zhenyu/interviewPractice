package zhenyu.sha.nb;
import java.util.*;
class Event implements Comparable<Event>{
    Event(int start, int type) {
        this.start = start;
        this.type = type;
    }
    // start index
    int start;
    // 0 in , 1 out
    int type;

    @Override
    public int compareTo(Event o) {
        int ret = Integer.compare(this.start, o.start);
        if(ret == 0) {
            ret = Integer.compare(this.type, o.type);
        }
        return ret;
    }
}
class MyCode {
    public int[] getFirstTimeSlot(int[][]p1, int[][]p2, int dur) {
        if(p1==null||p1.length==0||p2==null||p2.length==0)
            return null;
        ArrayList<Event> events = new ArrayList<>();
        for(int[] timeSlot: p1){
            events.add(new Event(timeSlot[0],0));
            events.add(new Event(timeSlot[1],1));
        }
        for(int[] timeSlot: p2){
            events.add(new Event(timeSlot[0],0));
            events.add(new Event(timeSlot[1],1));
        }

        Collections.sort(events);
        int count =0;
        int start = -1;
        for(Event e: events) {
            if(e.type==0){
                if(count==1) {
                    start = e.start;
                }
                count++;
            } else {
                if(count==2) {
                    //candidate
                    if(e.start-start>=dur) {
                        return new int[]{start, start+dur};
                    }
                }
                count--;
            }
        }
        return null;
    }
    public static void main (String[] args) {
        System.out.println("Hello Java");
        //int[][]  p1={{0, 20}, {35, 42}, {52, 80}};
        //int [][] p2={{8,15}, {22, 36}, {62, 100}};
        int[][]p1={{0,100}};
        int [][]p2={{0,1000}};

        int []ret = new MyCode().getFirstTimeSlot(p1, p2, 100);
        if(null!=ret) {
            System.out.println(ret[0]);
            System.out.println(ret[1]);
        } else {
            System.out.println("really?");
        }

    }
}



/*
p1=[[0, 20], [35, 42], [52, 80]]
p2=[[8,15], [22, 36], [62, 100]]
12

[62,74]

p1=[[0, 20], [35, 42], [52, 80]]
p2=[[8,15], [22, 36], [62, 100]]
7

[8, 15]

p1=[[0,20]]
p2=[[0,5], [8,15]]
7

p1=[[0,100]]
p2=[[0,10000]]
100
*/