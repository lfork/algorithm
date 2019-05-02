package practice;

import java.util.Arrays;

/**
 * Created by 98620 on 2019/5/2.
 * 思想：
 * 对数组进行遍历，然后每轮把【最大的数】移动到最后面去，相邻比较
 * <p>
 * 优化：对已经排好序的数组就不用再排了(没有交换)
 */
class MaxSort {
    public static void main(String[] args) {
        int[] array = {1, 6, 3, 7, 8, 0, -1, 23, 67, 32, 12, 90};

        boolean isOrdered = false;

        for (int i = 0; i < array.length; i++) {
            if (isOrdered){
                break;
            }
            isOrdered = true;
            int maxIndex = 0;

            int max = array[maxIndex];

            int j = 1;
            for (; j < array.length - i; j++) {
                if (max < array[j]) {
                    maxIndex = j;
                    max = array[maxIndex];
                } else {
                    isOrdered = false;
                }
            }

            int temp = array[j - 1];
            array[j - 1] = array[maxIndex];
            array[maxIndex] = temp;

        }
        System.out.println(Arrays.toString(array));
    }
}
