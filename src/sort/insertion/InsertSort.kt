package sort.insertion

import java.util.*

fun main(args: Array<String>) {
    val array = intArrayOf(-1, 0, 3, 1, 6, 7, 8, 2, 4, 9, 5, 5, 5)
    var temp: Int
    var position: Int

    for (i in 1 until array.size) {

        //save temp value
        temp = array[i]

        position = i
        //find the insert position
        for (k in 0 until i) {
            if (array[k] > temp) {
                position = k
                break
            }
        }

        //move elements and
        for (j in i downTo position + 1) {
            array[j] = array[j - 1]
        }

        //do insert
        array[position] = temp

    }
    println(Arrays.toString(array))
}