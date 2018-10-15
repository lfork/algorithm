package leetcode

/**
 *
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 * Created by 98620 on 2018/10/15.
 */


fun main(args: Array<String>) {
    val solution = Solution1()

    val result = solution.twoSum(intArrayOf(2, 7, 11, 15), 26)

    print(" ${result[0]}  ${result[1]}")
}

private class Solution1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var index1 = 0
        var index2 = 0

        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    index1 = i
                    index2 = j
                    return intArrayOf(index1,  index2)
                }
            }
        }

        return intArrayOf(index1,  index2)
    }
}
