package datamining.apriori;

import java.util.ArrayList;

public class ItemSet {
	private int supportCount = 0;

	private ArrayList<String> items = new ArrayList<>();

	public int getSupportCount() {
		return supportCount;
	}

	public void addSupportCount() {
		this.supportCount++;
	}

	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
	
	public void addItems(String item) {
		items.add(item);
	}

	@Override
	public String toString() {

		StringBuffer str = new StringBuffer();

		for (int i = 0; i < 1 && i < items.size(); i++) {
			str.append("{" + items.get(i));
		}

		for (int i = 1; i < items.size(); i++) {
			str.append("," + items.get(i));
		}

		str.append("}\t\t" + supportCount +"\n");
		return str.toString();
	}

}
