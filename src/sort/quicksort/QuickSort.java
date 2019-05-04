package sort.quicksort;

import java.util.Arrays;

/**
 * Created by 98620 on 2019/5/3. 思想： 1、取数组的第一个元素为pivot 中枢值
 * 2、把比pivot大的元素放到pivot右边，小的放在左边
 * 3、选择pivot两边的数组作为子数组，如果子数组的长度<=1，退出排序，否则，继续对子数组进行排序，重复步骤1和2
 */
class QuickSort{

    /*
     * 5,2,5,4,5 5,8,5,3,3,4
     * 
     * 5,5,5,1,2,3
     * 
     * 5,6,7,8
     * 
     * 5,6,7
     */
    public static void main(String[] args) {
        testCase1();

        // testCase2();

        // testCase3();

        // testCase4();

        // testCase5();
    }

    // 递归实现
    private static void qucikSort(int[] array, int startIndex, int endIndex) {

        if (startIndex >= endIndex || array == null || array.length <= 1) {
            return;
        }

        int low = startIndex;
        int high = endIndex;

        //ToDo 【待优化】 合理选取pivot，比如选取array[startIndex],array[endIndex]
        //array[(startIndex+endIndex)/2]三个值的中值作为pivotKey

        int pivot = array[low];

        // 对子数组进行快排
        while (low < high) {

      
            while (low < high && array[high] >= pivot) {
                high--;
            }

            //【优化】 对元素进行替代而不是交换
            if (low < high) {
                array[low] = array[high];
            }

            while (low < high && array[low] < pivot) {
                low++;
            }

            //【优化】 对元素进行替代而不是交换
            if (low < high) {
                array[high] = array[low];
            }
        }
        array[low] = pivot;
        System.out.println("low:" + low + " high:" + high);
        // 对左子数组进行排序
        qucikSort(array, startIndex, low - 1); // 0,0

        // 对右子数组进行排序
        qucikSort(array, low + 1, endIndex);// 0,1

    }

    private static void testCase1() {
        int[] array = { 1,2,3,4,5,5,6,7,8,9};
        qucikSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void testCase2() {
        int[] array = { 3, 5 };
        qucikSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void testCase3() {
        int[] array = { 3, 5, 3 };
        qucikSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void testCase4() {
        int[] array = { 5, 3, 5 };
        qucikSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void testCase5() {
        int[] array = { 123, 412, 3, 12, 41, 312, 3, 124, 12, 312, 4, 12, 3, 124, 12, 321 };
        qucikSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
