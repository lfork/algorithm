package datamining.apriori;

public class FrequentCollection extends CandidateCollection{
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("频繁项集").append("\n");
		str.append("项集\t\t支持度计数\t\n");
		for (ItemSet iset : getItems()) {
			str.append(iset).append("\n");
		}
		return str.toString();
	}
}
