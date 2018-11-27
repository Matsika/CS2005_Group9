package activityTracker;

public class ActivityData 
{
	private int elapsedTime;
    private int distance;
    private double altitude;
    private String date;
    
    public ActivityData(int elapsedTime, int distance, double altitude, String date)
    {
        this.elapsedTime = elapsedTime;
        this.distance = distance;
        this.altitude = altitude;
        this.date = date;
    }
    
    public int getElapsedTime()
    {
        return elapsedTime;
    }
    
    public int getDistance()
    {
        return distance;
    }
    
    public double getAltitude()
    {
        return altitude;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setElapsedTime(int elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }  
    
    public void setDistance(int distance)
    {
        this.distance = distance;
    } 
    
    public void setAltitude(double altitude)
    {
        this.altitude = altitude;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String toString()
    {
<<<<<<< HEAD
        return "ElapsedTime: " + elapsedTime + "  " + " Distance: " + distance + "  " + " Altitude: " + altitude + "  " +  " Date: " + date;
    }        
=======
        return "ElapsedTime: " + elapsedTime + " Distance: " + distance + " Final Altitude: " + altitude + " Date: " + date;
    }     
    
    public String[] justData() {
    	String[] dataThing = {Integer.toString(elapsedTime), Integer.toString(distance), Double.toString(altitude), date};
    	return dataThing;
    }
>>>>>>> branch 'final' of https://github.com/macp6/CS2005_Group9.git

}
