package datamining.apriori;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MathUtil {

	public static void main(String[] args) {
		String set[] = { "tea", "bread", "milk", "haha" };
//		String set[] = { "a", "b", "c", "d" };

		Set<Set<String>> result = MathUtil.getSubSet(set, 1); // 调用方法

		ArrayList<String> resultList = new ArrayList<>();
		// 输出结果
		for (Set<String> subSet : result) {
			StringBuffer item = new StringBuffer();
			for (String s : subSet)
				item.append(s);

			String s = item.toString();
			System.out.println(s);
			resultList.add(s);
		}

		System.out.println(" \n\n");

		String[][] realSubSetPair = getRealSubSetPair(set);
		for (int i = 0; i < realSubSetPair.length; i++) {
			System.out.println(realSubSetPair[i][0] + " " + realSubSetPair[i][1]);
		}
	}

	public static String[][] getRealSubSetPair(String[] set) {
		Set<Set<String>> result = new HashSet<Set<String>>(); // 用来存放子集的集合，如{{},{1},{2},{1,2}}
		int length = set.length;
		int num = length == 0 ? 0 : 1 << (length); // 2的n次方，若集合set为空，num为0；若集合set有4个元素，那么num为16.

		// 从range到2^n-1（[00...00]到[11...11]）
		for (int i = 1; i < num - 1; i++) {
			Set<String> subSet = new HashSet<String>();

			int index = i;
			for (int j = 0; j < length; j++) {
				if ((index & 1) == 1) { // 每次判断index最低位是否为1，为1则把集合set的第j个元素放到子集中
					subSet.add(set[j]);
				}
				index >>= 1; // 右移一位
			}

			result.add(subSet); // 把子集存储起来
		}

		ArrayList<String> resultList = new ArrayList<>();
		// 输出结果
		for (Set<String> subSet : result) {
			StringBuffer item = new StringBuffer();
			for (String s : subSet)
				item.append(s).append(" ");

			String s = item.toString();
			resultList.add(s);
		}

		int length2 = resultList.size();
		

		String[][] realSubSetPair = new String[length2 / 2][2];
		for (int i = 0; i < length2 / 2; i++) {
			String set1 = resultList.get(i);
			realSubSetPair[i][0] = resultList.get(i);
			
			StringBuffer set2 = new StringBuffer();
			for (String s : set) {
				if (set1.matches(".*"+ s+".*")) {
					continue;
				} else {
					set2.append(s).append(" ");
				}
				
			}
			realSubSetPair[i][1] = set2.toString();
		}
		return realSubSetPair;
	}

	public static <T> Set<Set<T>> getSubSet(T[] set) {
		return getSubSet(set, 0);
	}

	public static <T> Set<Set<T>> getSubSetNotNull(T[] set) {
		return getSubSet(set, 1);
	}

	private static <T> Set<Set<T>> getSubSet(T[] set, int range) {
		Set<Set<T>> result = new HashSet<Set<T>>(); // 用来存放子集的集合，如{{},{1},{2},{1,2}}
		int length = set.length;
		int num = length == 0 ? 0 : 1 << (length); // 2的n次方，若集合set为空，num为0；若集合set有4个元素，那么num为16.

		// 从range到2^n-1（[00...00]到[11...11]）
		for (int i = range; i < num; i++) {
			Set<T> subSet = new HashSet<T>();

			int index = i;
			for (int j = 0; j < length; j++) {
				if ((index & 1) == 1) { // 每次判断index最低位是否为1，为1则把集合set的第j个元素放到子集中
					subSet.add(set[j]);
				}
				index >>= 1; // 右移一位
			}

			result.add(subSet); // 把子集存储起来
		}
		return result;
	}

	/**
	 * 
	 * @param set
	 *            字符串集合
	 * @return  反正所有的X,Y对， N*2的矩阵。 比如X,Y属于U, X∩Y=空， 且 X,Y不为空
	 */
	public static String[][] getRealSubPair(String[] set) {
		return null;
	}

}
