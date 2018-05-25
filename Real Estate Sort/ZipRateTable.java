public class ZipRateTable {
	
	private int zip;
	private int rating;
	
	public ZipRateTable (int zip, int rating){
		super();
		this.zip = zip;
		this.rating = rating;
	}
	
	public int getZip()
	{
		return zip;
	}
	public void setZip(int zip)
	{
		this.zip = zip;
	}
	public int getRating()
	{
		return rating;
	}
	public void setRating(int rating)
	{
		this.rating = rating;
	}

}
