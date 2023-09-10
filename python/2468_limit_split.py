class Solution:
    def splitMessage(self, message: str, limit: int) :
        base =1 
        result = []
        while pow(10, base-1)<=len(message):
            r = self.splitMax(message, limit, base)
            if r is not None:
                return r
            base+=1
        return result
    def splitMax(self, message, limit, base):
        
        l =limit-4-base
        if l <=0:
            return None
        n = len(message)
        if n <= l:
            return [message+"<1/1>"]
        p = 1
        t = 0
        while n > 0 :
            if p> base or l <=0:
                return None
            c = int (n/l)
            c = min(c,pow(10, p)-pow(10,p-1) )
            t+=c
            n = n - c *l
            if n <=l and n>0 and c<pow(10, p)-pow(10,p-1):
                t+=1
                break
            p+=1    
            l = l-1
        i = 1
        begin = 0
        result =[]
        while i<=t:
           tag='<'+str(i)+'/'+str(t)+'>'
           s_len = limit - len(tag)
           result.append(message[begin: begin+s_len]+tag)
           begin+=s_len
           i+=1 
        return result

