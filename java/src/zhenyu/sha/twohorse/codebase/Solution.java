package zhenyu.sha.twohorse.codebase;
import java.util.*;
class Task {
    String name;
    int time;
    public Task(String name, int time) {
        this.name = name;
        this.time = time;
    }
}
public class Solution {
    public static List<String> schedule(Map<String, Integer> codebases, Map<String, Set<String>> deps) {
        // build the reverse dep map
        Map<String, Task> taskMap  = new HashMap<>();
        Map<String, Set<Task>> reverseDeps = new HashMap<>();
        for(Map.Entry<String, Integer> codebase : codebases.entrySet()) {
            taskMap.put(codebase.getKey(), new Task(codebase.getKey(), codebase.getValue()));
            reverseDeps.put(codebase.getKey(), new HashSet<>());
        }

        for(Map.Entry<String, Set<String>> dep: deps.entrySet()) {
            for( String task: dep.getValue()) {
                Set<Task> reverseDep = reverseDeps.get(task);
                if(null==reverseDep) {
                    reverseDep = new HashSet<>();
                }
                reverseDep.add(taskMap.get(dep.getKey()));
            }
        }
        // while not empty
        // try round
        // update the nodeps
        // TODO
        return null;
    }
}
