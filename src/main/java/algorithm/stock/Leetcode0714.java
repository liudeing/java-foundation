package algorithm.stock;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 题目
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2 输出: 8 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1 在此处卖出 prices[3] = 8 在此处买入 prices[4] = 4 在此处卖出 prices[5] = 9 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 */
public class Leetcode0714 {
    //这道题注意越界的问题
    public int maxProfit(int[] prices, int fee) {
        int hold = Integer.MIN_VALUE / 2;
        int sold = 0;

        for(int i = 0; i < prices.length; i++) {
            int hold2 = hold;
            int sold2 = sold;
            hold = Math.max(hold2, sold2 - prices[i]);
            sold = Math.max(sold2, hold2 + prices[i] - fee);
        }
        return sold;
    }
}
