class Solution:
    def nearestPalindromic(self, n: str) -> str:
        if (len(n)==1):
            return str(int(n)-1)
        l=0
        # left bound
        for i in range (len(n)-1):
            l = l * 10+9
        #right bound
        r = '1'
        for i in range (len(n)-1): 
            r += '0'
        r = int(r+'1')
        if int(n)-l < int(r)- int(n):
            r = str(l)
        c = int((len(n)-1)/2) 
        
        for i in range(-1, 2):
            first = n[:c]
            m = int(n[c])+i
            if m < 0 :
                m =9 
                first = str(int(first)-1) 
            if m> 9:
                m = 0
                first = str(int(first if len(first)>0 else "0")+1)
            p = first+  str(m)
            if len(n)%2 == 0:
                p += str(m)
            p+=first[::-1]
            if not p == n:
                delta_p = abs(int(p)-int(n))
                delta_r = abs(int(r)-int(n))
                if delta_p < delta_r or ( delta_p==delta_r and int(p) < int(r)):
                    r =p
        return str(r)

if __name__=='__main__':
    print (Solution().nearestPalindromic('11011'))