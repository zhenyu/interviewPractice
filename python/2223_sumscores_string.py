class Solution:
    def sumScores(self, s: str) -> int:
        ret = len(s)
        z=[0]
        l=0
        r=0 # exclusive
        for i in range (1, len(s)):
            
            if i< r:
                k = i-l
                if z[k]< r-i:
                    match = z[k]
                else:
                    match = r-i
                    while r < len(s) and s[r] == s[match]:
                        match+=1
                        r+=1
                    l=i
            else :
                # from begin
                l = i
                r = i
                match =0 
                while r<len(s) and s[r]==s[match]:
                    r+=1
                    match+=1

            z.append(match)
            ret+=match
                        
        return ret