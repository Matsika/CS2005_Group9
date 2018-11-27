package activityTracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

public class ReadCSV 
{
	private static final String COMMA_DELIMITER = ",";

    private static final int ELAPSEDTIME = 0;
    private static final int DISTANCE = 1;
    private static final int ALTITUDE = 2;
    private static final int DATE = 3;
    
    public ReadCSV() {
    	
    }
   
    public static void main(String[] args) throws FileNotFoundException 
    {
    	//I used the path where the file is located in my computer
    	//can change the path
    	File myFile = new File("/Users/TinotendaMatsika/git/CS2005_Group9FinalP/activityTracker/src/activityTracker/InputFormat.csv");
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
            
            for(ActivityData data: activeData)
            {
            	if(data.getElapsedTime() == 150) {

                    System.out.println(data.toString());
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

            }
            catch(IOException e)
            {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    
    }
    
    

}
