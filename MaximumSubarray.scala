// https://leetcode.com/problems/maximum-subarray

import java.lang.Math.max

object Solution {
  val start: SubArraySum = SubArraySum(0, Int.MinValue)
  def maxSubArray(nums: Array[Int]): Int = nums.foldLeft(start)(_ next _).maxSum
}

case class SubArraySum(currentSum: Int, maxSum: Int) {
  def next(n: Int): SubArraySum = {
    val newSum = if (currentSum > 0) currentSum + n else n
    SubArraySum(currentSum = newSum, maxSum = max(maxSum, newSum))
  }
}