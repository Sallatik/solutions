// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

import static java.lang.Math.*;

class Solution {

    public int maxProfit(int[] prices) {
        int [] maxProfitsStartingAt = findMaxProfitsStartingAt(prices);
        int buyAt = prices[0], maxProfit = 0;
        for (int current = 0; current < prices.length; current++) {
            buyAt = min(buyAt, prices[current]);
            int next = current + 1;
            int maxProfitStartingAtNext = next < maxProfitsStartingAt.length ? maxProfitsStartingAt[next] : 0;
            int currentProfit = prices[current] - buyAt;
            maxProfit = max(maxProfit, currentProfit + maxProfitStartingAtNext);
        }
        return maxProfit;
    }

    private int [] findMaxProfitsStartingAt(int [] prices) {
        int [] maxProfitsStartingAt = new int [prices.length];
        int sellAt = prices[prices.length - 1], maxProfit = 0;
        for (int current = prices.length - 1; current >= 0; current--) {
            sellAt = Math.max(sellAt, prices[current]);
            int currentProfit = sellAt - prices[current];
            maxProfit = max(maxProfit, currentProfit);
            maxProfitsStartingAt[current] = maxProfit;
        }
        return maxProfitsStartingAt;
    }
}