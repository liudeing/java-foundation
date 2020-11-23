package algorithm.stock;

/**
 * 121. 买卖股票的最佳时机
 * 题目
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4] 输出: 5 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。 示例 2:
 *
 * 输入: [7,6,4,3,1] 输出: 0 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */
public class Leetcode0121 {
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    public int maxProfit1(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            //经典的写法
            maxCur = Math.max(prices[i] - prices[i-1], maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }


    public int maxProfitByDp(int[] prices) {
        int[][] T = new int[prices.length][2];

        if(prices == null || prices.length<= 1) {
            return 0;
        }

        T[0][0] = 0;
        T[0][1] = -prices[0];

        for(int i = 1; i < prices.length; i++) {
            for(int j = 0; j <= 1; j++) {
                T[i][0] = Math.max(T[i-1][0], T[i-1][1] + prices[i]);
                T[i][1] = Math.max(T[i-1][1], - prices[i]);
            }
        }
        return T[prices.length - 1][0];
    }
}
