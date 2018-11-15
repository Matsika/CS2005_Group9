package activityTracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TinotendaMatsika
 */
public class Login {
    private String userName;
    private String password;

    public Login(String userName, String password)
    {
        this.password = password;
        this.userName = userName;
    } 
    
    public String getUserName()
    {
        return userName;
    }
    
    public String getPassword()
    {
        return password;
    } 
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }        
    
    public void setPassword(String password)
    {
        this.password = password;
    }        
    
    public boolean verifyAccountDetails(String password,String userName)
    {
        File inputFile = new File("CurrentUsersData.txt");
        try
        {
           Scanner in = new Scanner(inputFile);
           while(in.hasNextLine())
           {
               String line = in.nextLine();
           }    
        } 
        catch(FileNotFoundException ex)
        {
              System.out.println("File not Found");
        }   
        return true;
    }        
}
