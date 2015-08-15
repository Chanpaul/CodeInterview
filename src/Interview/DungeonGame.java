package Interview;

/**
 * Created by yongyangyu on 1/6/15.
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
