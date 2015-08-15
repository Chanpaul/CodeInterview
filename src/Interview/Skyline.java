package Interview;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by yongyangyu on 6/8/15.
 */
public class Skyline {
    static class Bldg {
        private int lx, rx, h;
        Bldg(int x1, int x2, int h) {
            this.lx = x1;
            this.rx = x2;
            this.h = h;
        }
    }
    static class Strip {
        private int lx, h;
        Strip (int x1, int h1) {
            this.lx = x1;
            this.h = h1;
        }
    }

    Strip[] strips;
    int count;
    int startLoc;
    public Skyline(int n) {
        count = 0;
        startLoc = 0;
        strips = new Strip[n];
    }

    public void Append(Strip str) {
        strips[startLoc + count] = str;
        count ++;
    }

    public Strip Head() {
        return strips[startLoc];
    }

    public Strip removeHead() {
        Strip str = strips[startLoc];
        count --; startLoc ++;
        return str;
    }

    public String toString() {
        String str = "";
        List<Strip> output = new ArrayList<>();



        for (int i = startLoc; i < startLoc + count; i ++) {
            if (output.isEmpty()) {
                output.add(strips[i]);
            }
            else {
                Strip last = output.get(output.size() - 1);
                if (last.lx == strips[i].lx && strips[i].h > last.h) {
                    output.remove(output.size() - 1);
                    output.add(strips[i]);
                }
                else if (last.lx < strips[i].lx && last.h == strips[i].h) {
                    continue;
                }
                else {
                    output.add(strips[i]);
                }
            }
        }
        for (Strip curr : output) {
            str = str + curr.lx + "," + curr.h + ";";
        }
        return str;
    }

    public Skyline findSkyline(Bldg[] B, int lo, int hi) {
        if (lo == hi) {
            Skyline sk = new Skyline(2);
            sk.Append(new Strip(B[lo].lx, B[lo].h));
            sk.Append(new Strip(B[lo].rx, 0));
            return sk;
        }
        int mid = lo + (hi - lo) / 2;
        Skyline sk1 = findSkyline(B, lo, mid);
        Skyline sk2 = findSkyline(B, mid + 1, hi);
        return mergeSkyline(sk1, sk2);
    }

    public Skyline mergeSkyline(Skyline sk1, Skyline sk2) {
        Skyline sk = new Skyline(sk1.count + sk2.count);
        int curH1 = 0, curH2 = 0;
        while (sk1.count > 0 && sk2.count > 0) {
            if (sk1.Head().lx < sk2.Head().lx) {
                int curX = sk1.Head().lx;
                curH1 = sk1.Head().h;
                int maxH = curH1;
                if (curH2 > maxH) {
                    maxH = curH2;
                }
                sk.Append(new Strip(curX, maxH));
                sk1.removeHead();
            }
            else {
                int curX = sk2.Head().lx;
                curH2 = sk2.Head().h;
                int maxH = curH1;
                if (curH2 > maxH) {
                    maxH = curH2;
                }
                sk.Append(new Strip(curX, maxH));
                sk2.removeHead();
            }
        }
        while (sk1.count > 0) {
            Strip str = sk1.removeHead();
            sk.Append(str);
        }
        while (sk2.count > 0) {
            Strip str = sk2.removeHead();
            sk.Append(str);
        }
        return sk;
    }

    public static void main(String[] args) {
        int n = 6;
        Bldg[] B = new Bldg[6];
        B[0] = new Bldg(1, 5, 11);
        B[1] = new Bldg(1, 7, 6);
        B[2] = new Bldg(3,9,13);
        B[3] = new Bldg(12,16,7);
        B[4] = new Bldg(14,25,3);
        B[5] = new Bldg(19,22,18);
        Skyline sk = new Skyline(0);
        Skyline res = sk.findSkyline(B, 0, n - 1);
        System.out.println(res);
    }
}
