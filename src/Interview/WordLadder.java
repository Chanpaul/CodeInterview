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
public class WordLadder {  // double-end BFS
    public List<List<String>> findLadders(String start, String end, Set<String> words) {
        Map<String, List<List<String>>> dictStart = new HashMap<>();
        Map<String, List<List<String>>> dictEnd = new HashMap<>();
        dictStart.put(start, new ArrayList<>());
        dictEnd.put(end, new ArrayList<>());
        dictStart.get(start).add(new ArrayList<>());
        dictEnd.get(end).add(new ArrayList<>());
        dictStart.get(start).get(0).add(start); dictEnd.get(end).get(0).add(end);
        words.remove(start); words.remove(end);
        List<List<String>> resultList = new ArrayList<>();
        while(dictStart.size() != 0 && dictEnd.size() != 0){
            Map<String, List<List<String>>> newDict = new HashMap<>();
            Map<String, List<List<String>>> tmpDict1, tmpDict2;
            Set<String> visited = new HashSet<>();
            tmpDict1 = dictEnd; tmpDict2 = dictStart;
            if(dictStart.size() < dictEnd.size()) { // choose a shorter position to start
                tmpDict1 = dictStart; tmpDict2 = dictEnd;
            }
            boolean findResults = false;
            for(Map.Entry<String, List<List<String>>> kv : tmpDict1.entrySet()){
                StringBuilder sb = new StringBuilder(kv.getKey());
                for(int i = 0; i < sb.length(); i++){
                    char ch = sb.charAt(i);
                    for(char rep = 'a'; rep <= 'z'; rep++){
                        if(rep == ch) continue;
                        sb.setCharAt(i, rep);
                        String newStr = sb.toString();
                        if(tmpDict2.containsKey(newStr)){
                            List<List<String>> list1 = tmpDict2.get(newStr), list2 = kv.getValue();
                            for(int j = 0; j < list1.size(); j++)
                                for(int k = 0; k < list2.size(); k++){
                                    List<String> result = new ArrayList<>(list1.get(j));
                                    for(int l = list2.get(k).size() - 1; l >= 0; l--) // reversely add
                                        result.add(list2.get(k).get(l));
                                    if(result.get(0) != start) Collections.reverse(result);
                                    resultList.add(result);
                                }
                            findResults = true;
                        }
                        else if(words.contains(newStr) && !findResults){
                            if(!visited.contains(newStr)) visited.add(newStr);
                            if(!newDict.containsKey(newStr)) newDict.put(newStr, new ArrayList<>());
                            for(List<String> list : kv.getValue()){
                                newDict.get(newStr).add(new ArrayList<>(list));
                                newDict.get(newStr).get(newDict.get(newStr).size() - 1).add(newStr);
                            }
                        }
                    }
                    sb.setCharAt(i, ch); //set back the original character
                }
            }
            if(findResults) break;
            words.removeAll(visited);
            if(tmpDict1 == dictStart) dictStart = newDict;
            else dictEnd = newDict;
        }
        return resultList;
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
