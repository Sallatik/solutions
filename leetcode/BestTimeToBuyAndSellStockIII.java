// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

class Solution {

    public int maxProfit(int[] prices) {
        int [] tailMax = tailMax(prices);
        int buy = prices[0], max = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, (prices[i] - buy) + (i == tailMax.length - 1 ? 0 : tailMax[i + 1]));
            buy = Math.min(buy, prices[i]);
        }
        return max;
    }

    private int [] tailMax(int [] prices) {
        int [] tailMax = new int [prices.length];
        int sell = prices[prices.length - 1], max = Integer.MIN_VALUE;
        for (int i = prices.length - 1; i >= 0; i--) {
            max = Math.max(max, sell - prices[i]);
            sell = Math.max(sell, prices[i]);
            tailMax[i] = max;
        }
        return tailMax;
    }
}