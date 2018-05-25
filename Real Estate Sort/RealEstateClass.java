public class RealEstateClass {

	private String Property_Type;
	private String Address;
	private String City;
	private int Zip;
	private int Price;
	private double Beds;
	private double Baths;
	private String Location;
	private int Square_Feet;
	private int Lot_Size;
	private int Year_Built;
	private int Days_On_Market;
	private int Dollar_Per_Sq_Ft;
	private int HOA_Per_Month;
	private int Rating;
	
	public RealEstateClass(String Property_Type, String Address, String City, 
			int Zip, int Price, double Beds, double Baths, String Location, 
			int Square_Feet, int Lot_Size, int Year_Built, int Days_On_Market,
			int Dollar_Per_Sq_Ft, int HOA_Per_Month) {
		super();
		this.Property_Type = Property_Type;
		this.Address = Address;
		this.City = City;
		this.Zip = Zip;
		this.Price = Price;
		this.Beds = Beds;
		this.Baths = Baths;
		this.Location = Location;
		this.Square_Feet = Square_Feet;
		this.Lot_Size = Lot_Size;
		this.Year_Built = Year_Built;
		this.Days_On_Market = Days_On_Market;
		this.Dollar_Per_Sq_Ft = Dollar_Per_Sq_Ft;
		this.HOA_Per_Month = HOA_Per_Month;
		
		
		if((this.Property_Type.equals("Single Family Residential")) && (this.Price < 200000) && (this.Dollar_Per_Sq_Ft < 110.0) && ((this.Square_Feet >= 1750) && (this.Square_Feet <= 2500)) && (this.HOA_Per_Month <= 25) && (this.Year_Built >= 2007))
		{
			this.Rating = 1;
		}
		else if((this.Property_Type.equals("Single Family Residential")) && (this.Price < 200000) && (this.Dollar_Per_Sq_Ft < 110.0) && ((this.Square_Feet >= 1750) && (this.Square_Feet <= 2500)) && ((this.HOA_Per_Month > 25) && (this.HOA_Per_Month <=30)) && (this.Year_Built >= 2007))
		{
			this.Rating = 2;
		}
		else if((this.Property_Type.equals("Single Family Residential")) && (this.Price < 200000) && (this.Dollar_Per_Sq_Ft < 110.0) && ((this.Square_Feet >= 1750) && (this.Square_Feet <= 2500)) && (this.HOA_Per_Month <= 25) && ((this.Year_Built >= 2000) && (this.Year_Built < 2007)))
		{
			this.Rating = 3;
		}
		else if((this.Property_Type.equals("Single Family Residential")) && (this.Price < 200000) && (this.Dollar_Per_Sq_Ft < 110.0) && ((this.Square_Feet >= 1750) && (this.Square_Feet <= 2500)) && ((this.HOA_Per_Month > 25) && (this.HOA_Per_Month <=30)) && ((this.Year_Built >= 2000) && (this.Year_Built < 2007)))
		{
			this.Rating = 4;
		}
		else
		{
			this.Rating = 0;
		}
		
		
		
	}
	public String getProperty_Type() {
		return Property_Type;
	}
	public void setProperty_Type(String Property_Type) {
		this.Property_Type = Property_Type;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String City) {
		this.City = City;
	}
	public int getZip() {
		return Zip;
	}
	public void setZip(int Zip) {
		this.Zip = Zip;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int Price) {
		this.Price = Price;
	}
	public double getBeds() {
		return Beds;
	}
	public void setBeds(double Beds) {
		this.Beds = Beds;
	}
	public double getBaths() {
		return Baths;
	}
	public void setBaths(double Baths) {
		this.Baths = Baths;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String Location) {
		this.Location = Location;
	}
	public int getSquare_Feet() {
		return Square_Feet;
	}
	public void setSquare_Feet(int Square_Feet) {
		this.Square_Feet = Square_Feet;
	}
	public int getLot_Size() {
		return Lot_Size;
	}
	public void setLot_Size(int Lot_Size) {
		this.Lot_Size = Lot_Size;
	}
	public int getYear_Built() {
		return Year_Built;
	}
	public void setYear_Built(int Year_Built) {
		this.Year_Built = Year_Built;
	}
	public int getDays_On_Market() {
		return Days_On_Market;
	}
	public void setDays_On_Market(int Days_On_Market) {
		this.Days_On_Market = Days_On_Market;
	}
	public int getDollar_Per_Sq_Ft() {
		return Dollar_Per_Sq_Ft;
	}
	public void setDollar_Per_Sq_Ft(int Dollar_Per_Sq_Ft) {
		this.Dollar_Per_Sq_Ft = Dollar_Per_Sq_Ft;
	}
	public int getHOA_Per_Month() {
		return HOA_Per_Month;
	}
	public void setHOA_Per_Month(int HOA_Per_Month) {
		this.HOA_Per_Month = HOA_Per_Month;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int Rating) {
		this.Rating = Rating;
	}
}
