package activityTracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
//


/**
 *
 * @author TinotendaMatsika
 */
public class CreateAccount implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
    private String password;
    private int age;
    private double height;
    //
    private double weight;
    private String toString;
    
    public CreateAccount(String userName,String password,int age,double height,double weight)
    {
    	if(verifyPassword(password) && verifyUserName(userName)) {
    		this.userName= userName;
    		this.password=password;
    		this.age=age;
    		this.height=height;
    		this.weight = weight;
    		this.toString = userName + "," + password + "," + age + "," + height + "," + weight;
    		BufferedWriter out = null;
    		

    		try {
    		    FileWriter fstream = new FileWriter("UserNameDatabase.txt", true); //true tells to append data.
    		    out = new BufferedWriter(fstream);
    		    out.write(this.toString);
    		    out.newLine();
    		}

    		catch (IOException e) {
    		    System.err.println("Error: " + e.getMessage());
    		}

    		finally {
    		    
    		    try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		    
    		}
    		
    	}
    	else {
    		System.out.println("Not Good Enough");
    	}
        
        
    }  
    
    public String getUserName()
    {
        return userName;
    } 
    
    public String getPassword()
    {
        return password;
    }  
    
    public int getAge()
    {
        return age;
    } 
    
    public double getHeight()
    {
        return height;
    } 
    
    public double getWeight()
    {
        return weight;
    } 
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }  
    
    public void setPassword(String password)
    {
        this.password = password;
    } 
    
    public void setAge(int age)
    {
        this.age = age;
    }   
    
    public void setHeight(double height)
    {
        this.height = height;
    }  
    
    public void setWeight(double weight)
    {
        this.weight = weight;
    }  
    
    //verify password
    public boolean verifyPassword(String password)
    {
        int specialCharacters = 0;
        int digitCount=0;
        int upperCaseChars = 0;
        int lowerCaseChars = 0;
        
        //password must be at least 8 characters long
        if(password.length() < 8)
        {
            return false;
        } 
        else
        {
            for(int i=0 ; i < password.length(); i++)  
            {
                char c = password.charAt(i);
                if(Character.isUpperCase(c))
                {
                   upperCaseChars++;
                }
                if(Character.isLowerCase(c))
                {    
                   lowerCaseChars++; 
                }
              
                if(Character.isDigit(c))
                {
                    digitCount++;
                }
                Pattern p = Pattern.compile("[^A-Za-z0-9]");
                Matcher m = p.matcher(password);  
                boolean b = m.find();
                if(b)
                {
                    specialCharacters++;
                } 
                
            } 
            
            //for the password to be valid it should have a least one uppercase letter,
            //one lowercase letter,at least one special character and at least one digit
            if(upperCaseChars >= 1 && lowerCaseChars >=1 && digitCount >=1 && specialCharacters >=1)
            {
                return true;
            } 
            else
            {
                return false;
            }     
        }    
        
    }  
    
    
    
    public boolean verifyUserName(String Username)
    {
        File inputFile = new File("UserNameDatabase.txt");
        try 
        {
            Scanner in = new Scanner(inputFile);
            
            while(in.hasNextLine())
            {
               String line = in.nextLine();
               String splitted[] = line.split(",",2);
               if(Username.equals(splitted[0]))
               {
                   System.out.println("Sorry that user name is taken");
                   return false;
               }
               else
               {
                         
               }    
            }
            in.close();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("File Not Found");
            
            return true;
        } 
        return true;
    }
    
    
    public static void main(String[] args)
    {        
        //CreateAccount newAccount = new CreateAccount("Emily","Lily!2507",21,148,38);
       
       
       
    }    
}
