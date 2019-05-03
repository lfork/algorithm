package search.binary

/**
 * @author 98620
 * 原理：
 * 二分搜索（英语：binary search），也称折半搜索（英语：half-interval search）[1]、对数搜索
 * （英语：logarithmic search）[2]，是一种在有序数组中查找某一特定元素的搜索演算法。
 * 搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；
 * 如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，
 * 而且跟开始一样从中间元素开始比较。如果在某一步骤数组为空，则代表找不到。
 * 这种搜索算法每一次比较都使搜索范围缩小一半。
 */

fun main(args: Array<String>) {
    val array = intArrayOf(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11)

    println(binarySearch(array, 8))
    println(binary(0, array.size - 1, array, 8))
}

fun binarySearch(array: IntArray, value: Int): Int {
    var head = 0
    var tail = array.size - 1
    var middle: Int
    while (head < tail) {
        middle = (head + tail) / 2
        if (array[middle] == value) {
            return middle
        } else if (array[middle] < value) {
            head = middle + 1
        } else {
            tail = middle - 1
        }

        println(""  + middle + " "  + head + " " + tail)
    }

    return -1
}

/**
 * @return 查找到的位置>=0
 */
fun binary(head: Int, tail: Int, array: IntArray, value: Int): Int {

    val middle = (head + tail + 1) / 2

    if (head < tail) {
        if (array[middle] == value) {
            return middle
        } else if (array[middle] < value) {
            return binary(middle + 1, tail, array, value)
        } else {
            return binary(head, middle - 1, array, value)
        }
    }

    return -1;
}