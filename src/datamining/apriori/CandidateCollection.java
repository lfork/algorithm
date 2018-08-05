package datamining.apriori;

import java.util.ArrayList;

public class CandidateCollection {
	private ArrayList<ItemSet> items = new ArrayList<>();
	
	public void addItems(ItemSet i) {
		items.add(i);
	}
	
	public ArrayList<ItemSet> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemSet> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		str.append("候选项集").append("\n");
		
		str.append("项集\t\t支持度计数\t\n");
		for (ItemSet iset : items) {
			str.append(iset).append("\n");
		}
		return str.toString();
	}

}
