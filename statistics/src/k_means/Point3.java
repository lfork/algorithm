package k_means; 

public class Point3 {
	
	public static double getDistance(Point3 pA, Point3 pB) {
		
		double base = Math.pow(pA.getX() - pB.getX(),2) + Math.pow(pA.getY() - pB.getY(),2) + Math.pow(pA.getZ() - pB.getZ(),2);
		
		return Math.pow(base, 0.5);
	}
	
	private int ID;
	
	private double X;
	
	private double Y;
	
	private double Z;

	
	public Point3() {
		
	}
	

	public Point3(double x, double y, double z) {
		super();
		X = x;
		Y = y;
		Z = z;
	}


	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getZ() {
		return Z;
	}

	public void setZ(double z) {
		Z = z;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		boolean result = super.equals(obj);
		
		if (result) {
			return result;
		} else {
			try {
				Point3 p = (Point3)obj;
				
				if (ID==p.getID()) {
					result = true;
				} else if (X==p.getX() && Y==p.getY() && Z==p.getZ()) {
					result= true;
				}
			} catch(Exception e) {
				result = false;
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return X + ","+Y + ","+ Z +"\n";
	}
	
	public Point3 setData(Point3 p) {
		X = p.getX();
		Y = p.getY();
		Z = p.getZ();
		
		return this;
	}
}
