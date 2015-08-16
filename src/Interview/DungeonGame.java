package Interview;

/**
 * Created by yongyangyu on 1/6/15.
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned
 * in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health
 * point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering
 * these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health
 * (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or
 * downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows
 * the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 *
 * -2 (K)	-3	   3
 * -5	   -10	   1
 * 10	    30	  -5 (P)
 *
 * Notes:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right
 * room where the princess is imprisoned.
 */
public class DungeonGame {
    private int getHp(int val) {
        return val <= 0 ? 1 : val;
    }
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) {
            return 0;
        }
        final int rows = dungeon.length;
        final int cols = dungeon[0].length;
        int[][] dp = new int[rows][cols];
        dp[rows-1][cols-1] = getHp(1 - dungeon[rows-1][cols-1]);
        for (int i = rows - 2; i >= 0; i --) {
            dp[i][cols-1] = getHp(dp[i+1][cols-1] - dungeon[i][cols-1]);
        }
        for (int j = cols - 2; j >=0; j --) {
            dp[rows-1][j] = getHp(dp[rows-1][j+1] - dungeon[rows-1][j]);
        }

        for (int i = rows-2; i >= 0; i --) {
            for (int j = cols - 2; j >= 0; j --) {
                dp[i][j] = getHp(Math.min(dp[i][j+1], dp[i+1][j]) - dungeon[i][j]);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonGame dg = new DungeonGame();
        int[][] arr = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(dg.calculateMinimumHP(arr));
    }
}
