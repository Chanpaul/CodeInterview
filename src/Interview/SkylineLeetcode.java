package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 6/8/15.
 * Suppose the input format is an array of triplets, i.e., (Lx, Rx, Hx) denoting the left coordinates,
 * the right coordinates, and the height of the building. For example,
 * Input: (1, 5, 11), (2, 7, 6), (3, 9, 13), (12, 16, 7), (14, 25, 3), (19, 22, 18)
 * Output: (1, 11), (3, 13), (9, 0), (12, 7), (16, 3), (19, 18), (22, 3), (25, 0)
 *
 * The algo is similar to merge sort in a divide-and-conquer fashion.
 * We define a class called Strip to denote an end-point of the skyline. Say, for building (1, 5, 11),
 * the list of (1, 11)->(5, 0) is used to describe the skyline of the building. Then the problem becomes
 * how to merge two existing skylines. Take the first two building as an illustration for merging two skylines.
 * sk1 = (1, 11)->(5, 0), sk2 = (2, 6)->(7, 0)
 * We maintain two variables, currH1 and currH2 to record the current height of the two skyline segment
 * we are inspecting now.
 * currH1 = 11, currH2 = 0 for the beginning.
 * Therefore, the merging result becomes sk12 = (1, 11). Now the algo checks the 2nd element from sk1 and
 * 1st element from sk2.
 * currH1 = 11, currH2 = 6, and sk12 = (1, 11)->(2, 11), and the pointer forwards for sk2.
 * currH1 = 0, currH2 = 6 since 5 < 7; and sk12 = (1, 11)->(2, 11)->(5, 6).
 * Now sk1 is empty and pointer of sk2 moves to the last element.
 * sk12 = (1, 11)->(2, 11)->(5, 6)->(7, 0).
 * Similarly, when sk3 = (3, 13)->(9, 0) arrives,
 * sk123 = (1, 11)->(3, 13)->(5,13)->(7,13)->(9,0).
 */
public class SkylineLeetcode {
    class Bldg {
        private int lx, rx, h;
        Bldg(int x1, int x2, int h) {
            this.lx = x1;
            this.rx = x2;
            this.h = h;
        }
    }

    class Strip {
        private int lx, h;
        Strip (int x1, int h1) {
            this.lx = x1;
            this.h = h1;
        }
    }

    class Skyline {
        private List<Strip> strips;

        public Skyline() {
            strips = new LinkedList<>();
        }

        public void append(Strip str) {
            strips.add(str);
        }

        public Strip head() {
            return strips.get(0);
        }

        public Strip removeHead() {
            return strips.remove(0);
        }

        public int count() {
            return strips.size();
        }

        public Skyline findSkyline(Bldg[] B, int lo, int hi) {
            if (lo == hi) {
                Skyline sk = new Skyline();
                sk.append(new Strip(B[lo].lx, B[lo].h));
                sk.append(new Strip(B[lo].rx, 0));
                return sk;
            }
            int mid = lo + (hi - lo) / 2;
            Skyline sk1 = findSkyline(B, lo, mid);
            Skyline sk2 = findSkyline(B, mid + 1, hi);
            return mergeSkyline(sk1, sk2);
        }

        public Skyline mergeSkyline(Skyline sk1, Skyline sk2) {
            Skyline sk = new Skyline();
            int curH1 = 0, curH2 = 0;
            while (sk1.count() > 0 && sk2.count() > 0) {
                if (sk1.head().lx < sk2.head().lx) {
                    int curX = sk1.head().lx;
                    curH1 = sk1.head().h;
                    int maxH = curH1;
                    if (curH2 > maxH) {
                        maxH = curH2;
                    }
                    sk.append(new Strip(curX, maxH));
                    sk1.removeHead();
                }
                else {
                    int curX = sk2.head().lx;
                    curH2 = sk2.head().h;
                    int maxH = curH1;
                    if (curH2 > maxH) {
                        maxH = curH2;
                    }
                    sk.append(new Strip(curX, maxH));
                    sk2.removeHead();
                }
            }
            while (sk1.count() > 0) {
                    Strip str = sk1.removeHead();
                    sk.append(str);
            }
            while (sk2.count() > 0) {
                Strip str = sk2.removeHead();
                sk.append(str);
            }
            return sk;
        }

    }

    public List<int[]> getSkyline(int[][] buildings) {
        int n = buildings.length;
        if (n == 0) {
            return new LinkedList<>();
        }
        Bldg[] B = new Bldg[n];
        for (int i = 0; i < n; i ++) {
            B[i] = new Bldg(buildings[i][0], buildings[i][1], buildings[i][2]);
        }
        Skyline sk = new Skyline().findSkyline(B, 0, n-1);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < sk.count(); i ++) {
            if (res.isEmpty()) {
                int[] elem = new int[2];
                elem[0] = sk.head().lx; elem[1] = sk.head().h;
                res.add(elem);
            }
            else {
                int[] last = res.get(res.size() - 1);
                if (last[0] == sk.strips.get(i).lx && sk.strips.get(i).h > last[1]) {
                    res.remove(res.size() - 1);
                    int[] elem = new int[2];
                    elem[0] = sk.strips.get(i).lx; elem[1] = sk.strips.get(i).h;
                    res.add(elem);
                }
                else if (last[0] < sk.strips.get(i).lx && last[1] == sk.strips.get(i).h) {
                    continue;
                }
                else {
                    int[] elem = new int[2];
                    elem[0] = sk.strips.get(i).lx; elem[1] = sk.strips.get(i).h;
                    res.add(elem);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2,9,10}, {3,7,15},{5,12,12}, {15,20,10},{19,24,8} };
        List<int[]> res = new SkylineLeetcode().getSkyline(buildings);
        for (int[] elem : res) {
            System.out.print(Arrays.toString(elem) + ",");
        }
    }
}
