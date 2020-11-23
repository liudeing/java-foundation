package algorithm.stock;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 题目
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 示例:
 *
 * 输入: [1,2,3,0,2] 输出: 3 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 */
public class Leetcode0309 {
    int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int[] s0 = new int[prices.length];
        int[] s1 = new int[prices.length];
        int[] s2 = new int[prices.length];

        //s1表示刚持仓
        s1[0] = -prices[0];
        //s1表示冷却
        s0[0] = 0;
        //s2表示刚卖出
        s2[0] = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);
            s2[i] = s1[i - 1] + prices[i];
        }
        return Math.max(s0[prices.length - 1], s2[prices.length - 1]);
    }

    int maxProfit1(int[] prices) {
        //表示当天持有股票
        int hold = Integer.MIN_VALUE;
        //表示当天空仓，但是还在CD
        int sold = 0;
        //表示当天空仓，CD完成
        int cooled = 0;

        for(int i = 0; i < prices.length; i++) {
            int hold2 = hold;
            int sold2 = sold;
            int cooled2 = cooled;
            hold =  Math.max(hold2,cooled2 - prices[i]);
            sold = hold2 + prices[i];
            cooled = Math.max(cooled2, sold2);
        }

        return Math.max(sold, cooled);
    }
}
