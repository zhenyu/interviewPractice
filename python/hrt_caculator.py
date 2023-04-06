class Unit(object):
    def Add(right: Unit)->Expr:
        return Expr('+', self, right)
        
    def Sub(right: Unit)->Expr:
        return Expr('-', self, right)
    
    def Mult(right: Expr)->Expr:
        return Expr('*', self, right)

class Lib(Unit):
    def __init__(self, value:str) -> None:
        super().__init__()
        self.value = value

    def toString():
        return self.value

    def simplify():
        return self
    
class Expr(Unit):

    def __init__(self, op: str, left: Unit, right:Unit) -> None:
        super().__init__()
        self.op = op
        self.left = left
        self.right = right

    def toString():
        return '('+self.left.toString()+ set.op+ self.right.toString()+')'

    def simplify():
        lvalue = int(self.left.simplify())
        rvalue = int(self.right.simplify())
        value =0
        if self.op.equals('+'):
            value = lvalue+rvalue
        elif self.op.equals('-'):
            value = lvalue - rvalue
        else:
            value = lvalue * rvalue
        return Lib(str(value))
