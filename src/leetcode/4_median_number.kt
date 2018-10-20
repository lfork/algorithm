package leetcode

/**
 *
 * Created by 98620 on 2018/10/20.
 *
 *
 */

fun main(args: Array<String>) {
    println(Solution4().findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)))
}

class Solution4 {

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {

        var maxLength = 0
        var minLength = 0
//        if (nums1.size > nums2.size) {
//            maxLength = nums1.size
//            minLength = nums2.size
//        } else {
//            maxLength = nums2.size
//            minLength = nums1.size
//        }


        //奇数和偶数的判断
        //奇数只需要取中间值
        //偶数的话去需要取n/2和 n/2 + 1来做算术平均


        //先做奇数  9/2=4
        val medianIndex = (nums1.size + nums2.size) / 2

        println(medianIndex)

        var i = 0
        var j = 0
        var index = 0
        var endWithFirstArray = false
        while (index < medianIndex) {
            while (i < nums1.size && nums1[i] < nums2[j]) {

                index++
                i++
                endWithFirstArray = true
            }

            while (j < nums2.size && nums2[j] < nums1[i]) {
                j++
                index++
                endWithFirstArray = false
            }
        }

        println("$i  $j")

        if (i == nums1.size && endWithFirstArray) {
            return nums1[i - 1].toDouble()
        }

        if (j == nums2.size && endWithFirstArray) {
            return nums2[j - 1].toDouble()
        }

        if (endWithFirstArray) {
            return nums1[i].toDouble()
        } else {
            return nums2[j].toDouble()
        }

        //再做偶数

    }

}