package algorithm.stock;

/**
 * 123. 买卖股票的最佳时机 III
 * 题目
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [3,3,5,0,0,3,1,4] 输出: 6 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。   随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 示例 2:
 *
 * 输入: [1,2,3,4,5] 输出: 4 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。 示例 3:
 *
 * 输入: [7,6,4,3,1] 输出: 0 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * @see https://www.youtube.com/watch?v=gsL3T9bI1RQ
 */
public class Leetcode0123 {

    public int maxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;

        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }

        return sell2;
    }

    public int maxProfit1(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far.
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

    public int maxProfit3(int[] prices) {
        int length = prices.length;
        int[] hold1 = new int[length + 1];
        int[] sold1 = new int[length + 1];
        int[] hold2 = new int[length + 1];
        int[] sold2 = new int[length + 1];
        hold1[0] = Integer.MIN_VALUE;
        hold2[0] = Integer.MIN_VALUE;
        for(int i = 0; i < length; i++){                              // Assume we only have 0 money at first
            sold2[i + 1] = Math.max(sold2[i], hold2[i] + prices[i]);     // The maximum if we've just sold 2nd stock so far.
            hold2[i + 1] = Math.max(hold2[i], sold1[i] - prices[i]);  // The maximum if we've just buy  2nd stock so far.
            sold1[i + 1] = Math.max(sold1[i], hold1[i] + prices[i]);     // The maximum if we've just sold 1nd stock so far.
            hold1[i + 1] = Math.max(hold1[i], 0 - prices[i]);          // The maximum if we've just buy  1st stock so far.
        }
        return sold2[length]; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }
}
