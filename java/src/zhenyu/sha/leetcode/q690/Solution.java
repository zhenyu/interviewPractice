package zhenyu.sha.leetcode.q690;

import java.util.*;
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

class Solution {

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = new HashMap<>();

        for(Employee e: employees){
            employeeMap.put(e.id, e);
        }
        return getSubImportance(employeeMap, id);
    }
    private int getSubImportance(Map<Integer, Employee> employeeMap, int id) {
        Employee root = employeeMap.get(id);
        int ret = root.importance;
        if (null!=root.subordinates){
            for(int subId : root.subordinates) {
                ret += getSubImportance(employeeMap,subId);
            }
        }
        return ret;
    }
}