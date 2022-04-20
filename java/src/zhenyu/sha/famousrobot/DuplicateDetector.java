package zhenyu.sha.famousrobot;
/*
def find_dupes(starting_dir: str) -> List[List[str]]
2	pass
 */

import java.util.*;
public class DuplicateDetector {
    public static List<List<String>> findDupes(String startingDir) throws Exception {
        if (null == startingDir || startingDir.isEmpty())
        {
            throw new Exception("invalid Paht");
        }
        Map<Long, List<String>> sizeFilesMap = new HashMap<>();
        dfs(startingDir, sizeFilesMap);

        return deDupByMD5(sizeFilesMap);
    }

    private static void dfs(String path, Map<Long, List<String>> sizeFilesMap) {
        if (!path.isEmpty()) {
            for (String file_path : path.split("")) {
                dfs(path + '/' + file_path, sizeFilesMap);
            }

        } else {
            long size = 1024;//fs.getContentlen(path);

            List<String> fileList = sizeFilesMap.get(size);

            if (null == fileList) {
                fileList = new LinkedList<>();
                sizeFilesMap.put(size, fileList);
            }
            fileList.add(path);
        }
    }

    private static List<List<String>> deDupByMD5(Map<Long, List<String>> sizeFilesMap) {
        List<List<String>> result = new LinkedList<>();
        for (List<String> files : sizeFilesMap.values()) {
            if (files.size() > 1) {
                Map<String, List<String>> sigFileMap = new HashMap<>();
                for (String file : files) {
                    String md5 = "";
                    List<String> fileList = sizeFilesMap.get(md5);

                    if (null == fileList) {
                        fileList = new LinkedList<>();
                        sigFileMap.put(md5, fileList);
                    }
                    fileList.add(file);
                }

            }
            for (List<String> filePaths : sizeFilesMap.values()) {
                if (filePaths.size()>1) {
                    result.add(filePaths);
                }
            }

        }
        return  result;
    }
}
