package sort.insertion;

import java.util.Arrays;

/**
 * 原理:
 * 插入排序（英语：Insertion Sort）是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
 * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，
 * 通常采用in-place排序 * （即只需用到 O(1)的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 * <p>
 * Principals:
 * First: scan
 * Second: save the temp value
 * Third: move value
 * Fourth: Insert
 * <p>
 * Then repeat the four steps until the data list scanned over.
 *
 * @author 98620
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {-1, 0, 3, 1, 6, 7, 8, 2, 4, 9, 5, 5, 5};
        insertSort(array, 0,array.length -1);
    }

    /**
     * asc sort
     */
    public static void insertSort(int[] array, int head, int tail) {

        int temp, position;
        for (int i = head + 1; i <= tail; i++) {

            //save temp value
            temp = array[i];

            position = i;
            //find the insert position
            for (int k = 0; k < i; k++) {
                if (array[k] > temp) {
                    position = k;
                    break;
                }
            }

            //move elements and
            for (int j = i; j > position; j--) {
                array[j] = array[j - 1];
            }

            //do insert
            array[position] = temp;

        }
        System.out.println(Arrays.toString(array));
    }
}
