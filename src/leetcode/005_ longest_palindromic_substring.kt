package leetcode

/**
 *
 * Created by 98620 on 2018/10/24.
 */
class Solution5 {
    fun longestPalindrome(s: String): String {
        if (s.isEmpty())
            return ""
        var maxLength = 0
        var maxPalStr = s[0].toString()
        var tempStr: String
        var elementNum: Int
        for (i in 0 until s.length - 1) {
            tempStr = s.substring(i)
            elementNum = tempStr.count { it == s[i] }
            for (j in 2..elementNum) {
                val index3 = indexOfSpecificTimesChar(tempStr, s[i], j)
                if (isPalindrome(tempStr.substring(0, index3 + 1)))
                    if (index3 + 1 > maxLength) {
                        maxLength = index3 + 1
                        maxPalStr = tempStr.substring(0, index3 + 1)
                    }
            }
        }

        return maxPalStr
    }


    private fun isPalindrome(str: String): Boolean {
        if (str.isEmpty()) {
            return false
        }
        for (i in 0 until str.length / 2) {
            if (str[i] != str[str.length - 1 - i])
                return false
        }
        return true
    }

    private fun indexOfSpecificTimesChar(s: String, ch: Char, specificTimes: Int): Int {
        var tempTimes = 0
        var index = -1
        for (i in 0 until s.length) {
            if (s[i] == ch) {
                tempTimes++

                if (tempTimes == specificTimes) {
                    index = i
                    break
                }
            }
        }
        return index
    }

}


fun main(args: Array<String>) {
//    println(Solution5().longestPalindrome("cbbdaaaa"))
//    println(Solution5().longestPalindrome("babad"))
    println(Solution5().longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//    println(Solution5().indexOfSpecificTimesChar("cbbdaaaa", 'a', 4))
//    println("adssadasdsa".count { it == 'a' })
}
