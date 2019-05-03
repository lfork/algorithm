package datamining.k_means;

import java.io.File;
import java.util.ArrayList;


/**
 * 对3维数据点分3个类的k-means算法
 * 
 * @author 98620 结束的标志：中心点不移动了
 */
public class Main {

	/**
	 * 需要输入参数才能运行
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("请输入数据源路径");
		}
		new Main().start(args[0]);
	}

	/**
	 * 3个label,区分是属于哪一个簇
	 */
	public final static int label1 = 0, label2 = 1, label3 = 2;

	/**
	 * 原始数据
	 */
	private ArrayList<Point3> originData = new ArrayList<>();

	/**
	 * 临时中心点1,2,3
	 */
	private Point3 centerPoint1, centerPoint2, centerPoint3;

	/**
	 * 临时中心点(上次的)preP1,2,3
	 */
	private Point3 preCenterPoint1, preCenterPoint2, preCenterPoint3;

	/**
	 * 临时簇1,2,3
	 */
	ArrayList<Point3> cluster1, cluster2, cluster3;

	private void start(String FilePath) {
		readData(FilePath);
		initData();
		classify();
		printResult();
	}

	private void readData(String path) {
		File file = new File(path);

		if (!file.exists()) {
			System.out.println("数据源文件不存在" + path);
			System.exit(0);
		}

		String data = FileHelper.readSpecificEncodingFile(path, "gbk");

		data = data.substring(data.indexOf("\n") + 1);// 删除第一行
		if (data.indexOf("\r") >= 0)
			data = data.substring(data.indexOf("\r") + 1);

		String[] strs = data.split("\n");

		try {
			for (String s : strs) {
				String[] items = s.split(",");
				Point3 p = new Point3();
				p.setID(Integer.parseInt(items[0]));
				p.setX(Double.parseDouble(items[1]));
				p.setY(Double.parseDouble(items[2]));
				p.setZ(Double.parseDouble(items[3]));
				originData.add(p);
			}
		} catch (Exception e) {
			System.out.println("数据源出错" + e);
			System.exit(0);
		}
	}

	
	/**
	 * 随机选择3个聚类中心
	 */
	private void initData() {
		
		if (originData.size() < 3) {
			System.out.println("数据长度太小 (<3)");
			System.exit(0);
		}
		
		centerPoint1 = new Point3().setData(originData.get(0));
		centerPoint2 = new Point3().setData(originData.get(1));
		centerPoint3 = new Point3().setData(originData.get(3));
		
		
		preCenterPoint1 = new Point3();
		preCenterPoint2 = new Point3();
		preCenterPoint3 = new Point3();

		
		saveCenterPoint();

		
		cluster1 = new ArrayList<>();
		cluster2 = new ArrayList<>();
		cluster3 = new ArrayList<>();
	}

	/**
	 * 
	 * 分类 （之前已经初始化过一次中心点了）{@link #initData()}
	 *
	 */
	private void classify() {
		//System.out.println("开始分类");
		while (true) {
			zeroSetting(); // 清理临时数据
			for (Point3 p : originData) {
				
				switch (getLable(p)) { // 获取标签，进行分类
				case label1:
					cluster1.add(p);
					break;
				case label2:
					cluster2.add(p);
					break;
				case label3:
					cluster3.add(p);
					break;
				}
			}
			
		

			// 计算新的中心点，
			calculateNewCenterPoint();
//			System.out.println("\n\n计算不出来吗1" + centerPoint1);
//			System.out.println("计算不出来吗2" + centerPoint2);
//			System.out.println("计算不出来吗3" + centerPoint3);

			if (isFinished()) {
				return; // 判断是否完成计算
			}

			// 保存当前的中心点 的值
			saveCenterPoint();
//			System.out.println("\n\n保存不了吗1" + centerPoint1);
//			System.out.println("保存不了吗2" + centerPoint2);
//			System.out.println("保存不了吗3" + centerPoint3);

		}
	}

	/**
	 * 将临时数据进行清理
	 */
	private void zeroSetting() {
		cluster1.clear();
		cluster2.clear();
		cluster3.clear();
	}

	
	/**
	 * 判断这个点属于哪个分类
	 * @param p 点
	 * @return 分类值
	 */
	private int getLable(Point3 p) {

		double distanceA = Point3.getDistance(p, centerPoint1);
		double distanceB = Point3.getDistance(p, centerPoint2);
		double distanceC = Point3.getDistance(p, centerPoint3);
		int label = label1;
		if (distanceA < distanceB) {
			label = label2;
			if (distanceB < distanceC) {
				label = label3;
			}
		} else {
			if (distanceA < distanceC) {
				label = label3;
			}
		}
		return label;
	}

	/**
	 * 判断是否分类结束 判断三个中心点是否停止变化了..其实在这里判断两个就行。因为2个中心点没有变了，那么另一个也一定不会变的
	 * 
	 * @return
	 */
	private boolean isFinished() {
		if (centerPoint1.equals(preCenterPoint1) && centerPoint2.equals(preCenterPoint2)) {
			return true;
		}
		return false;
	}

	/**
	 * 计算新的中心点
	 */
	private void calculateNewCenterPoint() {
		centerPoint1 = getCenterPoint(cluster1);
		centerPoint2 = getCenterPoint(cluster2);
		centerPoint3 = getCenterPoint(cluster3);
	}

	/**
	 * 根据簇(集合)里面的元素来求解该簇的中心点 分别求分类的X,Y,Z的均值得到新的中心点
	 */
	private Point3 getCenterPoint(ArrayList<Point3> pointList) {

		if (pointList == null || pointList.size() < 1) {
			System.out.println("簇里面的元素不能为空");
			return null;
		}

		int listLength = pointList.size();
		double sumX = 0, sumY = 0, sumZ = 0;

		for (Point3 p : pointList) {
			sumX += p.getX();
			sumY += p.getY();
			sumZ += p.getZ();
		}

		Point3 p = new Point3(sumX / listLength, sumY / listLength, sumZ / listLength);

		return p;
	}

	/**
	 * 保存当前中心点
	 */
	private void saveCenterPoint() {
		preCenterPoint1.setData(centerPoint1);
		preCenterPoint2.setData(centerPoint2);
		preCenterPoint3.setData(centerPoint3);
	}

	/**
	 * 结果输出
	 */
	private void printResult() {
		
		StringBuffer result = new StringBuffer();
		
		
		System.out.println("组1:" + cluster1.size());
		System.out.println("组2:" + cluster2.size());
		System.out.println("组3:" + cluster3.size());
		
		System.out.println("\n\n分组1 元素个数:" + cluster1.size());
		for (Point3 p : cluster1) {
			result.append(p);
			//System.out.println(p);
		}
		
		if (FileHelper.write(result.toString(), "D:/km_g1.csv")){
			System.out.println("结果输出到文件成功，保存路径" +"D:/km_g1.csv" );
		} else {
			System.out.println("结果输出到文件失败");
		}
		
		
		result.setLength(0);
		System.out.println("分组2 元素个数:" + cluster2.size());
		for (Point3 p : cluster2) {
			result.append(p);
			//System.out.println(p);
		}
		
		
		
		if (FileHelper.write(result.toString(), "D:/km_g2.csv")){
			System.out.println("结果输出到文件成功，保存路径" +"D:/km_g2.csv" );
		} else {
			System.out.println("结果输出到文件失败");
		}
		
		result.setLength(0);
		System.out.println("分组3 元素个数:" + cluster3.size());
		for (Point3 p : cluster3) {
			result.append(p);
			//System.out.println(p);
		}
		
		if (FileHelper.write(result.toString(), "D:/km_g3.csv")){
			System.out.println("结果输出到文件成功，保存路径" +"D:/km_g3.csv" );
		} else {
			System.out.println("结果输出到文件失败");
		}
		 
	}

}
