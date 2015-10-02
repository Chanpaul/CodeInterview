package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 12/7/14.
 * Version 1: (this version only looks for the transformation sequence size)
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the word list
 * For example,
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 *
 * Version 2:
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all "shortest" transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Return
 * [
 *    ["hit","hot","dot","dog","cog"],
 *    ["hit","hot","lot","log","cog"]
 * ]
 */
public class WordLadder {  // BFS for all the possible strings

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        Set<List<String>> paths = new HashSet<>();
        List<String> q = new ArrayList<>();
        q.add(beginWord);
        List<String> level = new ArrayList<>();
        Map<String, List<String>> parent = new HashMap<>();
        boolean find = false;
        Set<String> visited = new HashSet<>();
        for (String s: q) {  // mark the elements in q as visited
            visited.add(s);
        }
        while (!q.isEmpty()) {
            String curr = q.remove(0);
            StringBuilder last = new StringBuilder(curr);
            for (int i = 0; i < last.length(); i ++) {
                for (char c = 'a'; c <= 'z'; c ++) {
                    if (last.charAt(i) == c) {
                        continue;
                    }
                    char tmp = last.charAt(i);
                    last.setCharAt(i, c);
                    if (last.toString().equals(endWord)) {
                        find = true;
                        level.add(endWord);
                        if (!parent.containsKey(endWord)) {
                            List<String> fathers = new ArrayList<>();
                            fathers.add(curr);
                            parent.put(endWord, fathers);
                        }
                        else {
                            List<String> fathers = parent.get(endWord);
                            fathers.add(curr);
                            parent.put(endWord, fathers);
                        }
                    }
                    else if (wordList.contains(last.toString())) {
                        if (!parent.containsKey(last.toString())) {
                            List<String> fathers = new ArrayList<>();
                            fathers.add(curr);
                            parent.put(last.toString(), fathers);
                        }
                        else {
                            List<String> fathers = parent.get(last.toString());
                            fathers.add(curr);
                            parent.put(last.toString(), fathers);
                        }
                        level.add(last.toString());
                        visited.add(last.toString());
                    }
                    last.setCharAt(i, tmp);
                }
            }
            if (q.isEmpty() && level.size() > 0) {
                if (find) {
                    paths.addAll(recoverPath(parent, endWord));
                    return new ArrayList<>(paths);
                }
                else {
                    q = level;
                    level = new ArrayList<>();
                    for (String s : visited) {
                        wordList.remove(s);
                    }
                    visited.clear();
                }
            }
        }
        return new ArrayList<>(paths);
    }

    private List<List<String>> recoverPath(Map<String, List<String>> parent, String end) {
        List<List<String>> paths = new ArrayList<>();
        List<String> x = new LinkedList<>();
        x.add(end);
        paths.add(x);
        while (parent.containsKey(paths.get(0).get(0))) {
            List<List<String>> tmp = new ArrayList<>();
            for (List<String> part: paths) {
                String head = part.get(0);
                for (String pre: parent.get(head)) {
                    List<String> prepend = new LinkedList<>(part);
                    prepend.add(0, pre);
                    tmp.add(prepend);
                }
            }
            paths = tmp;
        }
        return paths;
    }

    public static int ladderLength(String start, String end, Set<String> dict) {
        if (start.length() != end.length()) {
            return 0;
        }
        if (start.equals("") || end.equals("")) {
            return 1;
        }
        if (dict.size() == 0) {
            return 0;
        }
        int dist = 1;
        List<String> q = new LinkedList<>();
        q.add(start);
        List<String> level = new LinkedList<>();
        while (!q.isEmpty()) {
            StringBuilder curr = new StringBuilder(q.remove(0));
            for (int i = 0; i < curr.length(); i ++) {
                for (char c = 'a'; c <= 'z'; c ++) {
                    if (curr.charAt(i) == c) {
                        continue;
                    }
                    char save = curr.charAt(i);
                    curr.setCharAt(i, c);
                    if (curr.toString().equals(end)) {
                        return dist + 1;
                    }
                    if (dict.contains(curr.toString())) {
                        level.add(curr.toString());
                        dict.remove(curr.toString());
                    }
                    curr.setCharAt(i, save);
                }
            }
            if (q.size() == 0 && level.size() > 0) {
                dist++;
                q = level;
                level = new LinkedList<>();
            }
        }
        return 0;
    }

    public static void main(String []args) {
        String start = "nape";
        String end = "mild";
        Set<String> dict = new HashSet<>();
        String [] words = {"dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier"};
        for (String s : words) {
            dict.add(s);
        }
        //System.out.println(ladderLength(start, end, dict));
        List<List<String>> paths = new WordLadder().findLadders(start, end, dict);
        for (List<String> x : paths) {
            System.out.println(Arrays.toString(x.toArray()));
        }
    }
}
