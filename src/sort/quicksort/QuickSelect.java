package sort.quicksort;

import sort.insertion.InsertSort;

/**
 * @author 98620
 * <p>
 * 找出第k个最大的元素
 */
public class QuickSelect {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77,
                9, 2, 9, 4, 10, 44, 69, 32, 58, 41, 56, 92, 85, 47, 63, 21, 45, 78, 62, 56, 12, 100, 3};

        quickSelect(arr, arr.length, arr.length - 1, 0, 10);
        System.out.println(arr[1]);
    }


    public static void quickSelect(int[] arr, int head, int tail, int k, int cutOff) {

        if (arr == null || arr.length <= 1 || head >= tail) {
            return;
        }

        if (tail - head > cutOff) {
            int pivot = arr[(head + tail) / 2];
            int i = head, j = tail;
            //循环完的结果是i>j
            while (i <= j) {
                while (arr[i] < pivot) {
                    i++;
                }
                while (arr[j] > pivot) {
                    j--;
                }
                if (i < j) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;

                    //在这里做加法和减法的原因：最后可能出现arr[i]=arr[j]==pivot，i<j的情况
                    //所以如果在外面的while等待i++或者是j--的话是可能无法做到的，然后就会出现死循环。可以看当前目录下的error1.png
                    i++;
                    j--;

                    //在这里做else if的原因：最后可能出现arr[i]=arr[j]==pivot，i==j的情况
                    //在外面的while里面也不能做i++和j--了，所以要在这里来做。可以看当前目录下的error2.png
                    //还有如果i==j的话可能是会产生死循环的
                } else if (i == j) {
                    i++;
                    System.out.println(i);
                }
                System.out.println(i);
            }

            if (j > k) {
                //pivot左边的数据进行一次排序
                quickSelect(arr, head, j,k,cutOff);
            } else {
                //pivot右边的数据也进行一次排序
                quickSelect(arr, i, tail,k,cutOff);
            }

        } else {
            InsertSort.insertSort(arr, head, tail);
        }

    }


}
