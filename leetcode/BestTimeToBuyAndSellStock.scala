// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

import java.lang.Math._

object Solution {
  def maxProfit(prices: Array[Int]): Int =
    prices.foldLeft(OptimalTransaction(prices(0), 0))(_ nextPrice _).profit
}

case class OptimalTransaction(buyAt: Int, profit: Int) {
  def nextPrice(price: Int): OptimalTransaction = OptimalTransaction (
    buyAt = min(buyAt, price),
    profit = max(profit, price - buyAt)
  )
}