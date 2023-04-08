from typing import *
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
                    dist_map.setdefault(d, set()).add(j)
        #iterate
        for i in range(len(points)-3):
            for j in range(i+1, len(points)-2):
                d01=self.dist(points[i], points[j])
                if d01>0:
                    for k in range(j+1, len(points)-1):
                        d02= self.dist(points[i], points[k])
                        if d02>0:
                            p3s1=dist_map.get(i).getdefault(d02,[])
                            p3s2=dist_map.get(k).getdefault(d01, [])
                        p3s = set(p3s1).intersection(set(p3s2))
                        for p3_index in p3s:
                            if self.dist(i, p3_index)==self.dist(j, k):
                                ret+=1  
        
        return ret

    def dist(self, p1:Tuple[int], p2: Tuple[int]):
        return (p1[0]-p2[0])**2+(p1[1]-p2[1])**2

if __name__ == '__main__':
    print(Solution().num_of_squares([0,1,1,0], [0,1,0,1]))