package sort.quicksort

fun main(args: Array<String>) {
    val arr = intArrayOf(1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10)
    quickSort(arr, 0, arr.size - 1)
    print(arr.asList())

}

fun quickSort(arr: IntArray, head: Int, tail: Int) {
    if (arr.size <= 1 || head >= tail) {
        return
    }

    val pivot = arr[(head + tail) / 2]
    var i = head
    var j = tail

    while (i < j){
        while (arr[i] < pivot) {
            i++
        }
        while (arr[j] > pivot) {
            j--
        }
        if (i < j) {
            val t = arr[i]
            arr[i] = arr[j]
            arr[j] = t

            //在这里做加法和减法的原因：最后可能出现arr[i]=arr[j]==pivot，i<j的情况
            //所以如果在外面的while等待i++或者是j--的话是可能无法做到的，然后就会出现死循环。可以看当前目录下的error1.png
            i++
            j--

            //在这里做else if的原因：最后可能出现arr[i]=arr[j]==pivot，i==j的情况
            //在外面的while里面也不能做i++和j--了，所以要在这里来做。可以看当前目录下的error2.png
            //还有如果
        } else if (i == j) {
            i++
        }
    }

    quickSort(arr,head,j)

    quickSort(arr,i,tail)


}