package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 2/4/16.
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from `JFK`. Thus,
 * the itinerary must begin with JFK.
 *
 * Note:
 * 1. If there are multiple valid itineraries, you should return the itinerary that has the smallest
 * lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller
 * lexical order than ["JFK", "LGB"].
 * 2. All airports are represented by three capital letters (IATA code).
 * 3. You may assume all tickets may form at least one valid itinerary.
 *
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */
public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        List<String> itinerary = new ArrayList<>();
        if (tickets.length == 0) return itinerary;
        Map<String, List<String>> fromTo = new HashMap<>();
        for (String[] x : tickets) {
            if (!fromTo.containsKey(x[0])) {
                List<String> q = new ArrayList<>();
                q.add(x[1]);
                fromTo.put(x[0], q);
            }
            else {
                fromTo.get(x[0]).add(x[1]);
            }
        }
        for (String key : fromTo.keySet()) {
            Collections.sort(fromTo.get(key));
        }
        String curr = "JFK";
        itinerary.add(curr);
        search(fromTo, curr, itinerary);
        return itinerary;
    }

    private boolean search(Map<String, List<String>> fromTo, String curr, List<String> itinerary) {
        if (fromTo.isEmpty()) return true;
        else if (!fromTo.containsKey(curr)) return false;
        else {
            List<String> q = fromTo.get(curr);
            int n = q.size();
            while (n > 0) {
                String next = q.remove(0);
                n --;
                itinerary.add(next);
                if (q.isEmpty()) fromTo.remove(curr);
                if (search(fromTo, next, itinerary)) return true;
                else {
                    q.add(itinerary.remove(itinerary.size()-1));
                    if (!fromTo.containsKey(curr))
                        fromTo.put(curr, q); // Be careful if curr is already deleted!
                }
            }
            if (!q.isEmpty()) return false;
        }
        if (fromTo.isEmpty()) return true; // return true only if fromTo is empty
        return false;
    }

    public static void main(String[] args) {
        String[][] tickets = {{"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},{"ANU","JFK"},
                {"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},{"EZE","ADL"},
                {"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},
                {"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}};
        ReconstructItinerary reconstruct = new ReconstructItinerary();
        List<String> res = reconstruct.findItinerary(tickets);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
