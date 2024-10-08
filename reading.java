
public class reading {
	private String region;
	private String country;
	private String state;
	private String city;
	private int month;
	private int day;
	private int year;
	private double avgTemperature;
	private reading next = null;

	// Constructor code
	public reading(String region, String country, String state, String city, int
	month, int day, int year, double avgTemperature)
	{
		this.region = region;
		this.country = country;
		this.state = state;
		this.city = city;
		this.month = month;
		this.day = day;
		this.year = year;
		this.avgTemperature = avgTemperature;
	}
	
	// Getters and setters
	
	public void setNext(reading n)
	{
		next = n;
	}
	
	public reading getNext()
	{
		return next;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public double getAvgTemperature() {
		return avgTemperature;
	}
	
	public void setAvgTemperature(double avgTemperature) {
		this.avgTemperature = avgTemperature;
	}
}
