// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

object Solution {
  def maxProfit(prices: Array[Int]): Int =
    prices.foldLeft(InsiderTrader(prices(0)))(_ leakNextPrice _).sellExistingStock.profit
}

case class InsiderTrader(currentPrice: Int, boughtAt: Option[Int], profit: Int) {
  def leakNextPrice(nextPrice: Int): InsiderTrader =
    trade(nextPrice).copy(currentPrice = nextPrice)

  def sellExistingStock: InsiderTrader = if (haveStock) sell else pass

  private def trade(nextPrice: Int): InsiderTrader =
    if (!haveStock && nextPrice > currentPrice) buy
    else if (haveStock && nextPrice < currentPrice) sell
    else pass

  private def haveStock: Boolean = boughtAt.isDefined
  private def buy: InsiderTrader = copy(boughtAt = Option(currentPrice))
  private def sell: InsiderTrader = copy(boughtAt = Option.empty, profit = profit + (currentPrice - boughtAt.get))
  private def pass: InsiderTrader = this
}

object InsiderTrader {
  def apply(firstPrice: Int): InsiderTrader = InsiderTrader(firstPrice, Option.empty, 0)
}