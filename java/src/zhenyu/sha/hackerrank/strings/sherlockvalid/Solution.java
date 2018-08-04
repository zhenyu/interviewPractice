package zhenyu.sha.hackerrank.strings.sherlockvalid;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        String s = "glwdyhrgcynwkwwxwnfxysjckddahgyvbydalgnaqrgrfflcdfrboeuepyensmihftjtmrdlqxckcpqkoprvngfsujbdlebpfxpqihwpisgsbyhhmalghnumpetgpgyduilvpvooeitefpdfjxmuropkmjjohvhtbyckqlcpicyutuybjcseingxiyfmalkqaoajsxvskfklghxufjnodqvekqlagihjgtlbowxmukwtespwemdgkiaxtvaimyrdlionrnallvjehlbgexnjvascogiwdqexkqthhaogbrjwcoxthmngebbkonufvesivabyuoymhubstifnfqovluvmlsixdcomarbimjbmtmltmcbusmddouqkaukhlkktdkpttnwlppcjcrwdbwgcxkjytaifywslenyhrcrwvueceqjakotjydmofmwiudvbjavclveqosixrmbnplkispknrifubflrjyamdhfjuedthjeeouyubfuvgvapjqljxxqrqhgcuacvxboncehvlryblcxjfdvsfmwnewampsyeiwlopshadvonskixwjglaafflircnaasrgpxiqsyyinhcgwibrnrehoduycvrtuchmfrhfxyonnojjyxvdscwnjgqcefiyarrogrtefcsdtbuacobptpxdwuqhpkxqsoyiheevibtdhmmsjjjnnvcdkwrwchpqtxnydyautygnvbwtuyadymugwrcdpubxswvyxkulgjvauyxtfgxewvnwsnjlbvxqyfijebrvdsqfpqpxdoeqfmupiwqwetqtqjqtqidldxjxuojjxprflxmapbmstpghejblpnvtauwlygdiomdnnqbsvptclcrsexnaekiqanobhoknftitkkxhrhrutfqgwmirousqnvkguqqamxgkobqglqkdcmvrbhwkrcafslmhogsepwrppmmrsvssqgkgcfbivhehfamwkwwcpkpstitbykkofh";
        String result = isValid(s);
        System.out.println(result);
    }
    static String isValid(String s) {
        List<Integer> counter = new ArrayList(26);

        for(int i =0; i<s.length();i++) {
            System.out.println("char="+s.charAt(i));
            int c = s.charAt(i)-'a';
            System.out.println("index="+c);
            if(counter.get(c) ==null){
                counter.add(c, new Integer(1));
            } else {
                counter.add(c, counter.get(c)+1);
            }
        }
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int i =0; i<counter.size();i++) {
            Integer count = counter.get(i);
            if(null!=count) {
                Integer times = counterMap.get(count);
                if(null == times) {
                    if( counterMap.size() >1) {
                        return "NO";
                    }
                    else {
                        counterMap.put(count, new Integer(1));
                    }
                } else {
                    times = times +1;
                }
            }
        }
        if(counterMap.size()<2)
            return "YES";
        int first = -1, firstTimes =0 , second=-1, secondTimes=0;
        for(Map.Entry<Integer, Integer> entry: counterMap.entrySet()) {
            if(first<0) {
                first = entry.getKey();
                firstTimes = entry.getValue();
            } else{
                second = entry.getKey();
                secondTimes = entry.getValue();
            }
        }
        if(second-first < 1 || second-first>1){
            return "NO";
        }
        return secondTimes==1||firstTimes==1? "YES":"NO";
    }
}