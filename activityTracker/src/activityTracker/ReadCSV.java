
package activityTracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class ReadCSV 
{
	private static final String COMMA_DELIMITER = ",";

    private static final int ELAPSEDTIME = 0;
    private static final int DISTANCE = 1;
    private static final int ALTITUDE = 2;
    private static final int DATE = 3;
    
   
   
    public static String[][] read() throws FileNotFoundException 
    {	
    	String[][] runs = new String[10][10];
    	int count = 0;
    	//I used the path where the file is located in my computer
    	//can change the path
    	File myFile = new File("C:\\Users\\macp1\\git\\final\\project\\activityTracker\\src\\activityTracker\\InputFormat.csv");
    	BufferedReader fileReader= null;
        try
        {
            ArrayList<ActivityData> activeData = new ArrayList<ActivityData>();
            
            String line = "";
            fileReader = new BufferedReader(new FileReader(myFile));
            fileReader.readLine();

            while((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0)
                {
                    
                    ActivityData data = new ActivityData(Integer.parseInt(tokens[ELAPSEDTIME]), Integer.parseInt(tokens[DISTANCE]), Double.parseDouble(tokens[ALTITUDE]), tokens[DATE]);
                    activeData.add(data);
                    
                    
                }    
            }
            Double prevAlt = 0.0;
        	Double altUp = 0.0;
        	Double altDown = 0.0;
            for(ActivityData data: activeData)
            {	
            	
            	
            	System.out.println("1");
            	if(prevAlt < data.getAltitude()) {
            		altUp += data.getAltitude() - prevAlt;
            		System.out.println(Double.toString(altUp));
           		}
           		else {
           			altDown += prevAlt - data.getAltitude();
           			System.out.println("3");
           		}
           		prevAlt = data.getAltitude();
            	
            	if (data.getElapsedTime() == 150) {
            		runs[count] = data.justData();
                    //System.out.println(runs[count].toString());
                    count++;
                    prevAlt = 0.0;
                    altUp = 0.0;
                    altDown = 0.0;
            	}
            	
            }    

        }
        catch(Exception e)
        {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileReader.close();
                return runs;

            }
            catch(IOException e)
            {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
                return null;
            }
        }
    
    }
    
    

}



