package custostomercountrysearchtask.task;

public class CustomerSearch {

	private String country;
	private int count;

	public CustomerSearch(String country, int count) {
		super();
		this.country = country;
		this.count = count;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CustomerSearch [country=" + country + ", count=" + count + "]";
	}
	
	

}
