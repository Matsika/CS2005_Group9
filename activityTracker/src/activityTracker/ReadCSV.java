
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream; 

public class ReadCSV 
{
	private static final String COMMA_DELIMITER = ",";

    private static final int ELAPSEDTIME = 0;
    private static final int DISTANCE = 1;
    private static final int ALTITUDE = 2;
    private static final int DATE = 3;
    
    public ReadCSV() {
    	
    }
   
    public static String[][] read(boolean byDate) throws FileNotFoundException 
    {	
    	;
    	int count = 0;
    	int entryCount = 1;
    	//I used the path where the file is located in my computer
    	//can change the path
    	File myFile = new File("C:\\Users\\macp1\\git\\final\\project\\activityTracker\\src\\activityTracker\\InputFormat.csv");
    	BufferedReader fileReader= null;
        
    	try {
    		String line = "";
            fileReader = new BufferedReader(new FileReader(myFile));
            fileReader.readLine();
            while((line = fileReader.readLine()) != null)
            {
            	System.out.println(line.split(COMMA_DELIMITER)[0]);
            	if(line.split(COMMA_DELIMITER)[0].equals("0")){
            		System.out.println("entry:" + entryCount);
            		entryCount++; 
            	}
                
            }
            
    	}catch(Exception e) {
    		System.out.println("nope");
    	}
    	
    	String[][] runs = new String[entryCount][6];
    	
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
            	
            	
            	if(prevAlt < data.getAltitude()) {
            		altUp += data.getAltitude() - prevAlt;
           		}
           		else {
           			altDown += prevAlt - data.getAltitude();
           		}
           		prevAlt = data.getAltitude();
            	
            	if (data.getElapsedTime() == 150) {
            		String[] thingy = {Integer.toString(data.getElapsedTime()), Integer.toString(data.getDistance()), Double.toString(data.getAltitude()), data.getDate(), Double.toString(altUp), Double.toString(altDown)};
            		runs[count] = thingy;
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
            	System.out.println(runs[0][1]);
                fileReader.close();
                if(byDate) {
                	return runs;
                }
                else {
                	return inOrder(runs);
                }

            }
            catch(IOException e)
            {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
                return null;
            }
        }//
    
    }
    public static String[][] inOrder(String[][] data){
    	String[][] newArray = new String[data.length][data[0].length];
    	ArrayList<Integer> distances = new ArrayList<Integer>();
    	int nextVal = 0;
    	for(int i=0; i<data.length;i++) {
    		distances.add(Integer.parseInt(data[i][1]));
    	}
    	for(int j = 0;j<data.length;j++) {
    		nextVal = distances.indexOf(Collections.max(distances));
    		newArray[j] = data[nextVal];
    		distances.set(distances.indexOf(Collections.max(distances)), -1);
    	}
    	
    	return newArray;
    	
    	
    }
    
    

}


