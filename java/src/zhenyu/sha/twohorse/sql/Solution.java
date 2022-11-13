package zhenyu.sha.twohorse.sql;
import java.util.*;
/**
 * # 没见过的新题。实现一个简单数据库，支持CREATE TABLE, INSERT 和 SELECT 语句，假设所有数据类型都是int，所有input都是有效的。
 * # input都是String[]的形式。INSERT返回一个unique_id, SELECT返回符合条件的id list，WHERE只要求支持>=, =, 和<=，以及AND.
 * # Example:
 * # {"CREATE", "TABLE", "table1", "(" "age", "height", ")"}
 * # {"INSERT", "INTO", "table1', "(", "20", "180", ")"}
 * # {"SELECT", "*", "FROM", "table1", "WHERE", "age", "=", "20", "AND", "height", ">="‍‌‍‍‍‍‍‍‍‍‍‌‍‍‍‌‍‍, "179"}
 */
public class Solution {
    public String exccute (List<String> sql) throws Exception{
        if(sql==null||sql.size()==0) {
            return "";
        }
        String result = null;
        switch (sql.get(0)){
            case "CREATE":
                result = doCreate(sql.subList(1, sql.size()));
                break;
            case "INSERT":
                result = doInsert(sql.subList(1, sql.size()));
            case "SELECT":
                result = doSelect(sql.subList(1, sql.size()));
            default:
                throw new Exception("only create/insert/select is supported");
        }
        return result;
    }

    private String doInsert(List<String> sql) throws  Exception{
        return null;
    }

    private String doCreate(List<String> subList) {
        return null;
    }

    private String doSelect(List<String> subList) {
        return null;
    }
}
