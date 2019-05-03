package sort.select;

import java.util.Arrays;

/**
 * 选择排序思想：
 * 选择排序（Selection sort）是一种简单直观的排序算法。
 * 它的工作原理如下。
 * 首先在未排序序列中找到最小（大）元素，
 * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 *
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。
 * 选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上，因此对 n个元素的表进行排序总共进行至多 n-1 次交换。
 * 在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 *
 * Principle:
 * First, find the max(minimum) element and save it to the start position.
 * Then continue to find the max(minimum) element in the rest data set
 * and put it to the bottom of the sorted sequence.
 * Repeat the task until whole elements was sorted.
 *
 * @author 98620
 */
public class SelectSort {
    public static void main(String[] args) {
        selectSort();
    }

    public static void selectSort(){
        int[] array = {3, 1, 6, 7, 8, 2, 4, 9, 5, 0};
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
