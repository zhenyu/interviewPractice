class Solution:
    def _dfs(self, pattern: str, s: str, p_start: int, s_start: int, mapping: dict):
        if p_start == len(pattern) and s_start == len(s):
            return True
        if p_start == len(pattern) or s_start == len(s):
            return False
        target_sub = mapping.get(pattern[p_start])
        if target_sub:
            if s[s_start:].startswith(target_sub):
                return self._dfs(pattern, s, p_start+1, s_start+len(target_sub), mapping)
        else:
            for i in range(s_start+1, len(s)+1):
                value = s[s_start: i]
                if value in mapping.values():
                    continue
                mapping[pattern[p_start]] = value
                if self._dfs(pattern, s, p_start+1, i, mapping):
                    return True
            mapping[pattern[p_start]] = None
        return False

    def wordPatternMatch(self, pattern: str, s: str) -> bool:
        mapping = {}
        return self._dfs(pattern, s, 0, 0, mapping)


if __name__ == '__main__':
    print(Solution().wordPatternMatch("abba",
"dogcatcatdog"))