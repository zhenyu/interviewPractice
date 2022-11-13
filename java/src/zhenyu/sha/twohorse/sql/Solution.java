package zhenyu.sha.twohorse.sql;
import java.util.*;
/**
 * # 没见过的新题。实现一个简单数据库，支持CREATE TABLE, INSERT 和 SELECT 语句，假设所有数据类型都是int，所有input都是有效的。
 * # input都是String[]的形式。INSERT返回一个unique_id, SELECT返回符合条件的id list，WHERE只要求支持>=, =, 和<=，以及AND.
 * # Example:
 * # {"CREATE", "TABLE", "table1", "(" "age", "height", ")"}
 * # {"INSERT", "INTO", "table1', "(", "20", "180", ")"}
 * # {"SELECT", "*", "FROM", "table1", "WHERE", "age", "=", "20", "AND", "height", ">=", "179"}
 */
interface Filter {
    boolean doFilter(List<Integer> value);
}
class BasicFilter implements Filter {
    int index;
    String op;
    int value;
    public BasicFilter (int index, int value, String op) {
        this.index = index;
        this.value = value;
        this.op = op;
    }
    @Override
    public boolean doFilter(List<Integer> value) {
        boolean ret = false;
        switch (op) {
            case "=":
                ret=value.get(index)==this.value;
                break;
            case ">=":
                ret= value.get(index) >=this.value;
                break;
            case "<=":
                ret=value.get(index)<=this.value;
                break;
        }
        return ret;
    }
}
class CombFilter implements Filter{
    List<Filter> filters;
    public CombFilter(){
        filters = new LinkedList<>();
    }
    public void addFilter(Filter filter) {
        filters.add(filter);
    }
    @Override
    public boolean doFilter(List<Integer> value) {
        for(Filter filter: filters) {
            if(!filter.doFilter(value)) {
                return false;
            }
        }
        return true;
    }
}
public class Solution {
    private Map<String, Map<String, Integer>> tableDefs;
    private Map<String, List<List<Integer>>> tableStorages;
    public  Solution() {
        this.tableDefs = new HashMap<>();
        this.tableStorages = new HashMap<>();
    }

    public String exccute (String sql) throws Exception{
        if(sql==null||sql.length()==0) {
            return "";
        }

        List<String> sqls = Arrays.asList( sql.split(" "));
        String result = null;
        switch (sqls.get(0)){
            case "CREATE":
                result = doCreate(sqls.subList(1, sqls.size()));
                break;
            case "INSERT":
                result = doInsert(sqls.subList(1, sqls.size()));
                break;
            case "SELECT":
                result = doSelect(sqls.subList(1, sqls.size()));
                break;
            default:
                throw new Exception("only create/insert/select is supported");
        }
        return result;
    }

    private String doInsert(List<String> sqls) throws  Exception{
        int index =0;
        //TODO check table
        index++;
        String tableName = sqls.get(index);
        index++;
        if (tableDefs.get(tableName)==null) {
            throw new Exception("the table has not been created: "+ tableName);
        }
        // TODO check the '('
        index++;
        List<Integer> row = new ArrayList<>(tableDefs.get(tableName).size());
        while (!sqls.get(index).equals(")")) {
            row.add(Integer.parseInt(sqls.get(index)));
            index++;
        }
        tableStorages.get(tableName).add(row);
        return Integer.toString(tableStorages.get(tableName).size()-1);
    }

    private String doCreate(List<String> sqls) throws Exception{
        int index =0;
        //TODO check table
        index++;
        String tableName = sqls.get(index);
        index++;
        if (tableDefs.get(tableName)!=null) {
            throw new Exception("can not recreate the table: "+ tableName);
        }
        Map<String, Integer> columIndexMap = new HashMap<>();
        int columnIndex =0;
        // TODO check the '('
        index++;
        while (!sqls.get(index).equals(")")) {
            columIndexMap.put(sqls.get(index), columnIndex);
            columnIndex++;
            index++;
        }
        tableDefs.put(tableName, columIndexMap);
        tableStorages.put(tableName, new LinkedList<>());
        return "";
    }

    private String doSelect(List<String> sqls) throws Exception {
        //build condition
        //  {"SELECT", "*", "FROM", "table1", "WHERE", "age", "=", "20", "AND", "height", ">=", "179"}
        int index =0;
        List<String> columnNames = new ArrayList<>();
        while (!sqls.get(index).equals("FROM")) {
            if(sqls.get(index).equals("*")) {
                columnNames.clear();
                index++;
                break;
            }
            columnNames.add(sqls.get(index));
            index++;
        }
        // sikp from
        index++;
        String tableName = sqls.get(index);
        if(!tableDefs.containsKey(tableName)) {
            throw new Exception(tableName+" has not been defined yet");
        }
        // skip table and where
        index+=2;
        List<Integer> outPutColumIndexes = new ArrayList<>(columnNames.size());
        if (columnNames.size()>0) {
            for(String columName : columnNames) {
                outPutColumIndexes.add(tableDefs.get(tableName).get(columName));
            }
        }
        CombFilter comboFilter = new CombFilter();
        while (index<sqls.size()) {
            if (sqls.get(index).equals("AND")){
                index++;
            } else {
                int columIndex = tableDefs.get(tableName).get(sqls.get(index));
                index++;
                String op = sqls.get(index);
                index++;
                int value = Integer.parseInt(sqls.get(index));
                index++;
                comboFilter.addFilter(new BasicFilter(columIndex, value, op));
            }
        }
        List<List<Integer>> result = new LinkedList<>();
        for (List<Integer> row: tableStorages.get(tableName)){
            if (comboFilter.doFilter(row)){
                if(outPutColumIndexes.size()==0){
                    result.add(row);
                } else {
                    List<Integer> outputRow = new ArrayList<>(outPutColumIndexes.size());
                    for(int outputIndex: outPutColumIndexes){
                        outputRow.add(row.get(outputIndex));
                    }
                    result.add(outputRow);
                }
            }
        }
        return result.toString();
    }
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        System.out.println(solution.exccute("CREATE TABLE table1 ( age height )"));
        System.out.println(solution.exccute("INSERT INTO table1 ( 20 180 )"));
        System.out.println(solution.exccute("INSERT INTO table1 ( 70 175 )"));
        System.out.println(solution.exccute("INSERT INTO table1 ( 50 170 )"));
        System.out.println(solution.exccute("SELECT * FROM table1 WHERE age >= 20 AND height <= 175"));
        //System.out.println(solution.exccute("SELECT height FROM table1 WHERE age <= 70"));

    }
}
