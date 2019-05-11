package sort.quicksort;

import java.util.Arrays;

/**
 * pivot取值优化
 * 
 * 交换变替换
 *
 * //TODO 值比较少的时候转换为插入排序
 */

class QuickSortOptimized {
    public static void main(String[] args) {
       

        System.out.println("xxx");
        int[] array = { 2, 3, 1, 213, 12, 4, 123, 12, 432, 14, 1325, 412, 5134, 312 };

        qSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        int target1 = 5134;
        System.out.println("target:" + target1 + " array里面的下标是:" + bSearch(array, target1, 0, array.length - 1));

        int target2 = -1;
        System.out.println("target:" + target2 + " array里面的下标是:" + bSearch(array, target2, 0, array.length - 1));

        int target3 = 14;
        System.out.println("target:" + target3 + " array里面的下标是:" + bSearch(array, target3, 0, array.length - 1));

        int target4 = 1;
        System.out.println("target:" + target4 + " array里面的下标是:" + bSearch(array, target4, 0, array.length - 1));

    }

    /**
     * 
     * @param sortedArray 元素不重复的有序数组,从小到大
     * @param target
     * @param startIndex
     * @param endIndex
     * @return targetIndex -1 表示查找失败
     */
    public static int bSearch(int[] sortedArray, int target, int startIndex, int endIndex) {
        // 参数合法性检查
        if (sortedArray == null || startIndex > endIndex) {
            return -1;
        }

        int middleIndex = (startIndex + endIndex) / 2;
        int middleValue = sortedArray[middleIndex];

        if (middleValue == target) {
            return middleIndex;
        } else if (middleValue > target) {
            return bSearch(sortedArray, target, startIndex, endIndex - 1);
        } else {
            return bSearch(sortedArray, target, startIndex + 1, endIndex);
        }
    }

    public static void qSort(int[] array, int startIndex, int endIndex) {
        if (array == null || startIndex >= endIndex) {
            return;
        }
        // 为了解决数组基本有序导致的“歪树”情景，这里需要对pivot进行优化
        setMiddleValue(array, startIndex, (startIndex + endIndex) / 2, endIndex);
        int pivot = array[startIndex];

        // 分治思想，pivot, 选值优化，递归优化，交换优化->替换

        while (startIndex < endIndex) {

            // 有一个while需要大于等于 pivot
            while (startIndex < endIndex && array[endIndex] >= pivot) {
                endIndex--;
            }

            array[startIndex] = array[endIndex];

            while (startIndex < endIndex && array[startIndex] < pivot) {
                startIndex++;
            }

            array[endIndex] = array[startIndex];

        }

        // 设置中间值
        array[startIndex] = pivot;

        qSort(array, 0, startIndex - 1);

        qSort(array, startIndex + 1, endIndex);

    }

    /**
     * @see #setMiddleValue
     */
    public static void runSetMiddleValueTestCase() {
        int[][] arrays = { { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 }, { 1, 1, 2 },
                { 1, 2, 1 }, { 2, 1, 1 }, { 1, 2, 2 }, { 2, 1, 2 }, { 2, 2, 1 }, { 1, 1, 1 } };

        int index = 0;
        for (int[] array : arrays) {
            System.out.println("\n\nindex" + index + "交换前:" + Arrays.toString(array));
            setMiddleValue(array, 0, 1, 2);
            System.out.println("index" + index++ + "交换后:" + Arrays.toString(array));
            
        }

    }

    /**
     * 把 middleVaule 放到 array[startIndex]
     * 
     * @param array
     * @param startIndex
     * @param middleIndex
     * @param endIndex
     */
    public static void setMiddleValue(int[] array, int startIndex, int middleIndex, int endIndex) {

        int x = array[startIndex];
        int y = array[middleIndex];
        int z = array[endIndex];

        if (x > y) {
            if (z < y) {
                array[startIndex] = y;
                array[middleIndex] = x;
            } else if (z < x) {
                array[startIndex] = z;
                array[endIndex] = x;
            }
        } else { // y >= x
            if (z > y) {
                array[startIndex] = y;
                array[middleIndex] = x;
            } else if (z > x) {
                array[startIndex] = z;
                array[endIndex] = x;
            }

        }
    }
}
