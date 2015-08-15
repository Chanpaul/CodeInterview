package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 5/12/15.
 */
public class TrieNode {
    private Map<Character, HashMap> root;

    public TrieNode() {
        root = new HashMap<Character, HashMap>();
    }

    public Map<Character, HashMap> getNode() {
        return root;
    }
}
