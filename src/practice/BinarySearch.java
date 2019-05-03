package practice;

/**
 * Created by 98620 on 2019/5/1.
 * <p>
 * 思想：
 * 折半查找需要在有序的数组里面进行查找。
 * 第一步，把目标元素和数组的中间元素进行比较
 * 第二步，如果目标元素等于中间元素，那么查找成功。
 * 如果小于，那么就把数组左半部分的元素取中间元素与目标元素进行比较(重复第一步、第二步)。
 * 如果大于，那么就把数组右半部分的元素取中间元素与目标元素进行比较(重复第一步、第二步)。
 */
class BinarySearch {

    public static void main(String[] args) {
        int[] orderedArray = {0, 1, 2, 9, 15,
                30, 36, 45, 78, 90,
                100, 125, 148, 236, 996};


        //lowIndex和highIndex表示数组元素最低的下标值和最高的下标值。而不是表示其他的东西。
        //比如下面的错误思想


        /*
        错误思想
        //因为整形的除2有两个单位的精度损失，所以low和high可以取两个值。
        //lowIndex 取0和-1都行
        // // highIndex 取orderedArray.length - 1,orderedArray.length都行

        解析：lowIndex-1 ：查比最小值还小的元素的时候会出现左数组越界
        lowIndex+1会查不到最左边的值
        highIndex = orderedArray.length：查比最小值还大的元素的时候会出现右边数组越界
         highIndex = orderedArray.length +1 同理
         */

        int lowIndex = 0;
        int highIndex = orderedArray.length - 1;
        int middleIndex;
        int middleValue;
        int targetValue = 996;
        int targetIndex = -1;

        //这里不能写lowIndex < highIndex，因为这样写无法查找到边界值
        while (lowIndex <= highIndex) {
            middleIndex = (lowIndex + highIndex) / 2;
            middleValue = orderedArray[middleIndex];
            if (targetValue == middleValue) {
                targetIndex = middleIndex;
                break;
            } else if (targetValue < middleValue) {
                highIndex = middleIndex - 1;
            } else {
                lowIndex = middleIndex + 1;
            }
        }

        if (targetIndex != -1) {
            System.out.println("查找成功，目标元素" + orderedArray[targetIndex] + "的下标为" + targetIndex);
        } else {
            System.out.println("查找失败，目标元素不存在");
        }
    }


//    public static int binarySearch(int[] arr, int start, int end, int hkey) {
//        int result = -1;
//
//        while (start <= end) {
//            int mid = start + (end - start) / 2;    //防止溢位
//            if (arr[mid] > hkey)
//                end = mid - 1;
//            else if (arr[mid] < hkey)
//                start = mid + 1;
//            else {
//                result = mid;
//                break;
//            }
//        }
//
//        return result;
//
//    }
}


