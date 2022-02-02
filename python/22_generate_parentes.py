class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        result = []
        self.dfs(1, 0, 0, [], result, 2*n)
        return result

    def dfs(self, pos: int, left_cnt: int, right_cnt: int, path: str, result:[], max: int) -> List[str]:
        if pos > max:
            if left_cnt == right_cnt:
                result.append(''.join(path))
            return
        # try ')'
        if left_cnt >= right_cnt+1:
            path.append(')')
            self.dfs(pos+1, left_cnt, right_cnt+1, path, result, max)
            path.pop()
        # try '('
        path.append('(')
        self.dfs(pos+1, left_cnt+1, right_cnt, path, result, max)
        path.pop()
if __name__=='main':
    for result in Solution().generateParenthesis(0):
        print(result)
