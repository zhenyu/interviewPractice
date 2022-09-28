package zhenyu.sha.leetcode.q588;
import java.util.*;
class Entry {
    Entry ( boolean isFile) {
        this.isFile = isFile;
        this.content = "";
        this.subEntries = new HashMap<>();
    }
    boolean isFile;
    String content;
    Map<String, Entry> subEntries;
}
class FileSystem {
    Entry root;
    public FileSystem() {
        this.root = new Entry(false);
    }

    public List<String> ls(String path) {
        if (path.compareTo("/")==0){
            return collectDir(root);
        }
        String[] paths = path.split("/");
        Entry cur = root;
        String p="";
        for ( int i =1 ;i<paths.length; i++) {
            p = paths[i];
            cur = cur.subEntries.get(p);
        }
        if (cur.isFile) {
            List<String> result = new LinkedList<>();
            result.add(p);
            return result;
        }
        return collectDir(cur);
    }
    private List<String>collectDir(Entry dir) {
        List<String> result = new ArrayList<>(dir.subEntries.size());
        result.addAll(dir.subEntries.keySet());
        Collections.sort(result);
        return result;
    }
    public void mkdir(String path) {
        String[] paths = path.split("/");
        Entry cur = root;
        String p="";
        for ( int i =1 ;i<paths.length; i++) {
            p = paths[i];
            Entry next = cur.subEntries.get(p);
            if (next == null) {
                next = new Entry( false);
                cur.subEntries.put(p, next);
            }
            cur = next;
        }

    }

    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        Entry cur = root;
        String p="";
        for ( int i =1 ;i<paths.length; i++) {
            p = paths[i];
            Entry next = cur.subEntries.get(p);
            if (next == null) {
                next = new Entry( false);
                cur.subEntries.put(p, next);
            }
            cur = next;
        }
        cur.isFile = true;
        cur.content += content;
    }

    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        Entry cur = root;
        String p="";
        for ( int i =1 ;i<paths.length; i++) {
            p = paths[i];
            cur = cur.subEntries.get(p);
        }
        return cur.content;
    }
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.ls("/");                         // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        fileSystem.ls("/");                         // return ["a"]
        fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
    }
}
