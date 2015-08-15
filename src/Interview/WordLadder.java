package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 12/7/14.
 */
public class WordLadder {  // BFS for all the possible strings
    public static ArrayList<ArrayList<String>> getPath(String start, String end, Set<String> dict) {
        ArrayList<ArrayList<String>> path = new ArrayList<ArrayList<String>>();
        if (start.length() != end.length()) {
            return null;
        }
        if (start.equals("") || end.equals("")) {
            return null;
        }
        if (dict.size() == 0) {
            return null;
        }
        ArrayList<ArrayList<String>> q = new ArrayList<ArrayList<String>>();
        ArrayList<String> curr = new ArrayList<String>();
        curr.add(start);
        q.add(curr);
        ArrayList<ArrayList<String>> level = new ArrayList<ArrayList<String>>();
        boolean find = false;
        HashMap<String, Integer> use = new HashMap<String, Integer>();
        while (!q.isEmpty()) {
            curr = q.remove(0);
            StringBuilder last = new StringBuilder(curr.get(curr.size() - 1));
            for (int i = 0; i < last.length(); i ++) {
                for (char c = 'a'; c <= 'z'; c ++) {
                    if (last.charAt(i) == c) {
                        continue;
                    }
                    char tmp = last.charAt(i);
                    last.setCharAt(i, c);
                    if (last.toString().equals(end)) {
                        find = true;
                        ArrayList<String> copy = new ArrayList<String>(curr);
                        copy.add(end);
                        level.add(copy);
                    }
                    else if (dict.contains(last.toString())) {
                        ArrayList<String> copy = new ArrayList<String>(curr);
                        copy.add(last.toString());
                        level.add(copy);
                        use.put(last.toString(), 1);
                        //dict.remove(last.toString());
                    }
                    last.setCharAt(i, tmp);
                }
            }
            if (q.isEmpty() && level.size() > 0) {
                if (find) {
                    path = new ArrayList<ArrayList<String>>();
                    for (ArrayList<String> x : level) {
                        if (x.get(x.size() - 1).equals(end)) {
                            path.add(x);
                        }
                    }
                    return path;
                }
                else {
                    for (ArrayList<String> x : level) {
                        q.add(x);
                    }
                    level = new ArrayList<ArrayList<String>>();
                    for (String s : use.keySet()) {
                        dict.remove(s);
                    }
                    use.clear();
                }
            }
        }
        return path;
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
        List<String> q = new LinkedList<String>();
        q.add(start);
        LinkedList<String> level = new LinkedList<String>();
        while (!q.isEmpty()) {
            StringBuilder curr = new StringBuilder(q.remove(0));
            for (int i = 0; i < curr.length(); i ++) {
                for (char c = 'a'; c <= 'z'; c ++) {
                    if (curr.charAt(i) == c) {
                        continue;
                    }
                    char tmp = curr.charAt(i);
                    curr.setCharAt(i, c);
                    if (curr.toString().equals(end)) {
                        return dist + 1;
                    }
                    if (dict.contains(curr.toString())) {
                        level.add(curr.toString());
                        dict.remove(curr.toString());
                    }
                    curr.setCharAt(i, tmp);
                }
            }
            if (q.size() == 0 && level.size() > 0) {
                dist++;
                for (String x : level) {
                    q.add(x);
                }
                level = new LinkedList<String>();
            }
        }
        return 0;
    }

    public static void main(String []args) {
        String start = "nape";
        String end = "mild";
        Set<String> dict = new HashSet<String>();
        String [] words = {"dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier"};
        for (String s : words) {
            dict.add(s);
        }
        //System.out.println(ladderLength(start, end, dict));
        ArrayList<ArrayList<String>> path = getPath(start, end, dict);
        for (ArrayList<String> x : path) {
            System.out.print("[");
            for (String s : x) {
                if (!s.equals(x.get(x.size() - 1))) {
                    System.out.print(s + ", ");
                }
                else {
                    System.out.print(s);
                }
            }
            System.out.println("]");
        }
    }
}
