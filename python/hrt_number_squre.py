from typing import *
import bisect

class  Solution(object):
    def num_of_squares(self, xs:List[int], ys:List[int]):
        ret =0
        p_dist_map = {}
        points =zip(xs, ys)
        points = sorted(points)

        for i in range(len(points)):
            dist_map = {}
            p_dist_map[i] = dist_map
            for j in range (i+1, len(points)):
                d=self.dist(points[i], points[j])
                if d >0:
                    dist_map.setdefault(d, []).append(j)
        #iterate
        for p0_index in range(len(points)-3):
            for p1_index in range(p0_index+1, len(points)-2):
                d=self.dist(points[p0_index], points[p1_index])
                if d>0:
                    p2s = p_dist_map.get(p0_index).get(d)
                    start =bisect.bisect_right(p2s,p1_index)
                    for k in range(start, len(p2s)):
                        p2_index = p2s[k]
                        p3s1=p_dist_map.get(p1_index).get(d, [])
                        p3s2=p_dist_map.get(p2_index).get(d, [])
                        p3s = set(p3s1).intersection(set(p3s2))
                        for p3_index in p3s:
                            if self.dist(points[p0_index], points[p3_index])==self.dist(points[p1_index], points[p2_index]):
                                ret+=1   
        
        return ret

    def dist(self, p1:Tuple[int], p2: Tuple[int]):
        return (p1[0]-p2[0])**2+(p1[1]-p2[1])**2

if __name__ == '__main__':
    print(Solution().num_of_squares([0,0,1,1,0,-1,0,2,2], [0,0,1,0,1,1,2,0,2]))