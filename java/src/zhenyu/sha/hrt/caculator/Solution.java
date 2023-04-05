package zhenyu.sha.hrt.caculator;
interface Expr {
    Expr Add(Expr e);
    Expr Sub(Expr e);
    Expr Multi(Expr e);
    Expr Div(Expr e);
    String toString();
    int simplify();
}
abstract class BaseExpr implements  Expr {
    @Override
    public Expr Add(Expr e) {
        return new Var(Var.OP.ADD, this, e);
    }

    @Override
    public Expr Sub(Expr e) {
        return new Var(Var.OP.SUB, this, e);
    }

    @Override
    public Expr Multi(Expr e) {
        return new Var(Var.OP.MULTI, this, e);
    }

    @Override
    public Expr Div(Expr e) {
        return new Var(Var.OP.DIV, this, e);
    }

}
class Lib extends BaseExpr {
    String number;
    int value;
    Lib(String number) {
       this.number = number;
       this.value = Integer.parseInt(number);
    }

    @Override
    public int simplify() {
        return value;
    }
    @Override
    public String toString() {
        return number;
    }
}
class Var extends BaseExpr {
    enum OP {
        ADD,
        SUB,
        MULTI,
        DIV
    }
    OP op;
    Expr left;
    Expr right;
    Var(OP op, Expr left, Expr right) {
        this.op =op;
        this.left = left;
        this.right =right;
    }

    @Override
    public int simplify() {
        if(OP.ADD ==op) {
            return left.simplify()+right.simplify();
        }
        if(OP.SUB ==op) {
            return left.simplify()-right.simplify();
        }
        if(OP.MULTI ==op) {
            return left.simplify()*right.simplify();
        }
        return left.simplify()/ right.simplify();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(left.toString());
        if(OP.ADD ==op) {
            sb.append("+");
        }
        else if(OP.SUB ==op) {
            sb.append("-");
        }
        else if(OP.MULTI ==op) {
            sb.append("*");
        } else  {
            sb.append("/");
        }
        sb.append(right.toString()).append(")");
        return sb.toString();
    }
}
public class Solution {
    public static void main(String[] args) {
        Expr two = new Lib("2");
        System.out.println( two+ "," + two.simplify());
        Expr plusThree = two.Add(new Lib("3"));
        System.out.println( plusThree+ "," + plusThree.simplify());
        Expr fourFive = new Lib("4").Add(new Lib("5"));
        Expr mul = fourFive.Multi(fourFive);
        System.out.println( mul+ "," + mul.simplify());
    }
}
