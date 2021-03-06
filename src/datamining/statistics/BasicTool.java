package datamining.statistics;

public class BasicTool {

	public static double getMeanValue(double... args) {
		double result = 0f;
		if (args != null) {
			int length = args.length;
			double sum = 0f;
			for (int i = 0; i < length; i++) {
				sum+=args[i];
			}
			
			result = sum / length;
			
		}
		return result;
	}
	
	public static double getVariance(double... args) {
		double meanValue = getMeanValue(args);
		double result = 0;
		if (args != null) {
			int length = args.length;
			double sum = 0;
			for (int i = 0; i < length; i++) {
				sum+= Math.pow((args[i] - meanValue) , 2);
			}
			result = sum / (length - 1);
		}
		return result;
	}
	
	public static double getStandardDeviation(double... args) {
		return Math.pow(getVariance(args), 0.5);
	}
	
	
}
