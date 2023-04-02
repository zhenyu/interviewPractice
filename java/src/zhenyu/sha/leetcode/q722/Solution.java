package zhenyu.sha.leetcode.q722;
import java.util.*;
public class Solution {
    public List<String> removeComments(String[] source) {
        List<String> result = new LinkedList<>();
        boolean inComment = false;
        StringBuilder sb = new StringBuilder();
        for(String line: source) {
            int i =0;
            while(i<line.length()) {
                char c = line.charAt(i);
                i++;
                if(!inComment) {
                    if (c=='/' && i<line.length()) {
                        if(line.charAt(i)=='/'){
                            break;
                        } else if (line.charAt(i)=='*') {
                            inComment = true;
                            i++;
                            continue;
                        }
                    }
                    sb.append(c);

                } else {
                    if (c=='*' && i<line.length()&&line.charAt(i)=='/') {
                        i++;
                        inComment = false;
                    }

                }
            }
            if (!inComment && sb.length()>0) {
                result.add(sb.toString());
                sb.setLength(0);
            }
        }
        return result;
    }
}