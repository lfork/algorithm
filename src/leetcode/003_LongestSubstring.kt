package leetcode

import java.util.*

/**
 *
 * Created by 98620 on 2018/10/18.
 *
 * Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
fun main(args: Array<String>) {
//    "asddsa".
    Solution().lengthOfLongestSubstring("dvdf")
}

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        val subStrings = ArrayList<StringBuilder>()
        var strBuf = StringBuilder()
        subStrings.add(strBuf)
        s.forEach {
            if (strBuf.contains(it)) {
                val newBuf =  StringBuilder()
                val repeatIndex = strBuf.indexOf(it)
                if (repeatIndex + 1 < strBuf.length) {
                    newBuf.append(strBuf.substring(repeatIndex+1))
                }
                strBuf = newBuf
                subStrings.add(strBuf)
            }
            strBuf.append(it)
        }
        strBuf = subStrings.maxBy { it.length }!!
        return strBuf.length;
    }
}

//1、如果有多个不同的长度相同的最长字符串，那么就算第一个字符串
//


//分析：
//1、需要遍历字符串

