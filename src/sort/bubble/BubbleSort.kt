package sort.bubble

import java.util.*

/**
 * @author 98620
 * Bubble Sort implementation with Kotlin.
 */
fun main(args: Array<String>) {
    val array = intArrayOf(3, 1, 6, 7, 8, 2, 4, 9, 5, 0)
    for (i in array.indices) {
        // -1 for no overflow and
        // -i for less traversal times, because the last item would be fixed after every time traversal
        for (j in 0 until array.size - 1 - i) {
            if (array[j] > array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
    println(Arrays.toString(array))
}
