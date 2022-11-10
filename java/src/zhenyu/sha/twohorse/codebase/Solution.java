package zhenyu.sha.twohorse.codebase;
import java.util.*;

public class Solution {
    public static List<String> schedule(Map<String, Integer> codeBases, Map<String, Set<String>> deps) {

        Map<String, Integer> depDegrees = new HashMap<>();
        Map<String, Set<String>> reverseDep = new HashMap<>();
        // build reverse
        for(Map.Entry<String,Set<String>> depEntry: deps.entrySet()) {
            String curNode = depEntry.getKey();
            Set<String> curDeps = depEntry.getValue();
            // setDegree
            depDegrees.put(curNode, curDeps.size());
            for(String curDep : curDeps) {
                Set<String> curDepRevers = reverseDep.getOrDefault(curDep, new HashSet<String>());
                curDepRevers.add(curNode);
                reverseDep.put(curDep,curDepRevers);
            }
        }
        List<String> result = new LinkedList<>();
        LinkedList<String> noDepCodes = new LinkedList<>();
        for (String codeId: codeBases.keySet()) {
            if (null==deps.get(codeId)) {
                noDepCodes.add(codeId);
            }
        }
        StringBuilder leveOutput = new StringBuilder();
        while (noDepCodes.size()>0) {
            leveOutput.setLength(0);
            int levelTime = Integer.MAX_VALUE;
            for(String noDepCode : noDepCodes) {
                levelTime = Math.min(levelTime, codeBases.get(noDepCode));
            }
            leveOutput.append(levelTime);
            int preSize = noDepCodes.size();
            for(int i =0; i<preSize;i++) {
                String noDepCode = noDepCodes.pollFirst();
                leveOutput.append(",");
                leveOutput.append(noDepCode);
                int remainTime = codeBases.get(noDepCode)-levelTime;
                codeBases.put(noDepCode, remainTime);
                if (remainTime ==0) {
                    for(String curReverseDep: reverseDep.getOrDefault(noDepCode, new HashSet<>())){
                        int degree = depDegrees.get(curReverseDep)-1;
                        depDegrees.put(curReverseDep, degree);
                        if(degree ==0) {
                            noDepCodes.addLast(curReverseDep);
                        }
                    }
                } else {
                  noDepCodes.addLast(noDepCode);
                }
            }
            result.add(leveOutput.toString());
        }
        return result;
    }
    public static void main (String[] args) {
         /**
          * codebases = {'A':1, 'B':2, 'C':3, 'D':3, 'E':3}
         **/
        Map<String, Integer> codebases = new HashMap<>();
        codebases.put("A", 1);
        codebases.put("B", 2);
        codebases.put("C",3);
        codebases.put("D",3);
        codebases.put("E", 3);
        Map<String, Set<String>> deps = new HashMap<>();
        /**
        ** dependency = {
                'B': ['A'],
        'C': ['A'],
        'D': ['A', 'B'],
        'E': ['B', 'C']
        }
        * */
        Set<String> depB = new HashSet<>();
        depB.add("A");
        deps.put("B", depB);

        Set<String> depC = new HashSet<>();
        depC.add("A");
        deps.put("C", depC);

        Set<String> depD = new HashSet<>();
        depD.add("A");
        depD.add("B");
        deps.put("D", depD);

        Set<String> depE = new HashSet<>();
        depE.add("B");
        depE.add("C");
        deps.put("E", depE);

        List<String> schedule = schedule(codebases,deps);
        System.out.println(schedule.toString());
    }
}

