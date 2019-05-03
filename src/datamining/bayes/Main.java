package datamining.bayes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
	private ArrayList<String[]> originData = new ArrayList<>();
	private String[] attributes;
	private String[] instanceX;
	private String classNode;

	/**
	 * 每个元素出现的频数
	 */
	private HashMap<String, Integer> frequency = new HashMap<>();

	/**
	 * key 类节点的取值 ,value类节点取值出现的频数
	 */
	private HashMap<String, Integer> classValues = new HashMap<>();
	private ArrayList<String> values = new ArrayList<>();
	private int instancesNumber = 0;

	public static void main(String[] args) {
		if (args.length == 2) {
			try {
				new Main().start(args[0], args[1]);
			} catch (Exception e) {
				System.out.println("文件路径或者格式错误，数据读取失败");
				System.out.println("程序按照测试数据继续运行\n");
				
				try {
					new Main().start("src/bayes/dataset.txt", "青绿,稍蜷,浊响,清晰,凹陷,硬滑");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} else {
			System.out.println("请输入正确的数据路径和测试实例");
		}
	}

	private void start(String path, String X) throws Exception {
		readFile(new File(path));
		getTestInstance(X);
		analyseClassNode();
		statistic();
		getResult();
	}

	/**
	 * 分析类节点，默认是取的最后一个属性作为类节点，并计算类节点的值出现的频数 然后再根据类节点对测试实例的计数进行初始化 然后再判断是几类问题
	 * 统计实例个数，输出属性集合和类节点的信息
	 */
	private void analyseClassNode() {
		for (String[] strings : originData) {
			String v = strings[strings.length - 1];
			Integer fre = classValues.get(v);
			if (fre != null) {
				fre = fre + 1;
				classValues.put(v, fre);
			} else {
				classValues.put(v, 1);
				values.add(v);
			}

		}

		System.out.print("类节点的取值: ");
		for (String string : values) {
			System.out.print(string + " ");

		}

		for (String string : instanceX) { // 对测试属性的频数进行初始化
			for (String str : values) {
				frequency.put(string + "|" + str, 0);
			}
		}

		System.out.println("\n这是一个" + values.size() + "类问题");
		instancesNumber = originData.size();
		System.out.println("实例个数:\t" + instancesNumber);
		System.out.print("属性集合:\t");
		for (String string : attributes) { // 遍历打印
			System.out.print(string + " ");
		}
		classNode = attributes[attributes.length - 1];
		System.out.println("\n类节点:\t\t" + classNode);
		System.out.println(
				classNode + "的值为" + values.get(0) + "的概率:" + classValues.get(values.get(0)) * 1.0 / instancesNumber);

	}

	/**
	 * 从文件中读取数据
	 * @param file 数据源
	 */
	private void readFile(File file) {
		String data = FileHelper.load(file.getPath());
		String firstLine = data.substring(0, data.indexOf("\n"));
		firstLine = firstLine.replaceAll(" *", "");
		firstLine = firstLine.substring(firstLine.indexOf(",") + 1);// 去掉第一列
		attributes = firstLine.split(",");
		data = data.replaceAll("\\d+,", ""); // 去掉数字
		data = data.replaceAll(" *", ""); // 去掉空格

		data = data.substring(data.indexOf("\n") + 1); // 去掉第一行
		if (data.indexOf("\r") >= 0)
			data = data.substring(data.indexOf("\r") + 1);
		String[] strs = data.split("\n");
		for (String s : strs) {
			String[] items = s.split(",");
			originData.add(items);
		}
	}

	/**
	 * 对原始数据进行统计 获取实例个数 统计每个测试节点所占的频数
	 */
	private void statistic() {
		for (String[] strings : originData) {
			String poleType = strings[strings.length - 1];
			
			for (int i = 0; i < strings.length - 1; i++) {
				Integer fre = frequency.get(strings[i] + "|" + poleType);
				if (fre != null) {
					fre = fre + 1;
					frequency.put(strings[i] + "|" + poleType, fre);
				}
			}
		}

	}

	/**
	 * 计算测试结果
	 */
	private void getResult() {
		// 现在要根据类节点的取值来进行计算,分别算每个值下面的分类模型的值
		HashMap<String, Double> modelValues = new HashMap<>();
		String result = null;
		double maxValue = 0;
		for (String string : values) {
			double modelValue = 0;
			double probabilityOfClassNode = classValues.get(string) * 1.0 / instancesNumber;
			modelValue = probabilityOfClassNode;
			for (String x : instanceX) { // 对测试属性的频数进行初始化
				modelValue = modelValue * frequency.get(x + "|" + string) * 1.0 / classValues.get(string);
			}
			modelValues.put(string, modelValue);
			if (modelValue > maxValue) {
				maxValue = modelValue;
				result = string;
			}
			System.out.println("测试实例在类节点【" + classNode + "】的值为【" + string + "】下的模型值：" + modelValues.get(string));
		}
		System.out.println("所以朴素贝叶斯分类器将此实例分类为:" + result);
	}

	/**
	 * 解析测试实例
	 * @param X 测试实例的字符串
	 */
	private void getTestInstance(String X) {
		System.out.println("测试实例:\t" + X);
		X = X.replaceAll(" *", "");
		instanceX = X.split(",");
	}
}
