package sort.quicksort;

public class QuickSort {

    public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
        myQuickSort(arr, 0, arr.length - 1);
        StringBuilder out = new StringBuilder();
        for (int digit : arr) {
            out.append(digit).append(",");
        }
        System.out.println(out);
    }


    /**
     * 找到一个pivot，然后把比pivot大的元素放到右边，小的放到左边
     */
    public static void myQuickSort(int[] arr, int head, int tail) {
        if (arr == null || arr.length <= 1 || head >= tail) {
            return;
        }

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

        //pivot左边的数据进行一次排序
        myQuickSort(arr, head, j);

        //pivot右边的数据也进行一次排序
        myQuickSort(arr, i, tail);
    }
}