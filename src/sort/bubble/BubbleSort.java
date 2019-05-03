package sort.bubble;



import java.util.Arrays;

/**
 * 冒泡排序算法的运作如下：
 *
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 * @author 98620
 */
public class BubbleSort {
    public static void main(String[] args) {
        bubbleSort();
    }

    public static void bubbleSort() {
        int[] array = {3, 1, 6, 7, 8, 2, 4, 9, 5, 0};
        for (int i = 0; i < array.length; i++) {
            // -1 for no overflow and
            // -i for less traversal times, because the last item would be fixed after every time traversal
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }


    /**
     * 优化后的冒泡算法
     * 1、因为每冒泡一次，最后一个元素的值就已经固定了，所以可以通过-i来每次减少一次遍历
     * 2、添加交换flag 。如果在某次的交换过程中，没有出现元素的交换。那么说明排序已经完成了
     */
    public static void optmizeBubbleSort() {
        int[] array = {3, 1, 6, 7, 8, 2, 4, 9, 5, 0};
        boolean flag;
        for (int i = 0; i < array.length; i++) {
            //因为每冒泡一次，最后一个元素的值就已经固定了，所以可以通过-i来每次减少一次遍历
            //减1是因为要用到j+1，防止数组越界
            flag = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }

        }
        System.out.println(Arrays.toString(array));
        System.out.println(array[0]);
    }


    /**
     * 甩锅交换算法
     */
    public static void other() {
        int[] array = {3, 1, 6, 7, 8, 2, 4, 9, 5, 0};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
