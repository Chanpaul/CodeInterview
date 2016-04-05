package Interview;

/**
 * Created by yongyangyu on 4/5/16.
 * Twitter OA
 * You are given a jar, which contains white beans and red beans. Each round you pick a bean from the jar, if the
 * bean is white, you eat; otherwise, put it back and pick again. You eat the bean whatever color it is.
 * What is the probability that the last bean you eat is white?
 *
 * Let P(w, r) = Pr(last bean if white | current w white beans and r red beans)
 * P(w, r) = 0 if w = 0
 * P(w, r) = 1 if r = 0 and w > 0
 * P(w, r) = w/(w+r)*P(w-1, r) + r/(w+r)*[w/(w+r)*P(w-1, r) + r/(w+r)*P(w, r-1)]
 */
public class BeansProbability {
    public double probabilityLastBeanWhite(int white, int red) {
        if (white <= 0) {
            return 0.0;
        }
        else if (red <= 0) {
            return 1.0;
        }
        else {
            double[][] prob = new double[white+1][red+1];
            for (int w = 1; w < prob.length; w ++) {
                prob[w][0] = 1.0;
            }
            for (int w = 1; w < white + 1; w ++) {
                for (int r = 1; r < red + 1; r ++) {
                    prob[w][r] = w * 1.0 / (w+r) * prob[w-1][r] + r * 1.0 / (w+r) *
                            (w * 1.0 / (w+r) * prob[w-1][r] + r * 1.0 / (w+r) * prob[w][r-1]);
                }
            }
            return prob[white][red];
        }
    }

    public static void main(String[] args) {
        int white = 2, red = 2;
        BeansProbability bp = new BeansProbability();
        System.out.println(bp.probabilityLastBeanWhite(white, red));
    }
}
