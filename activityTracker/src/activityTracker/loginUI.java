

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import CreateAccount;
//import ReadCSV;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.filechooser.*;

//test

public class loginUI {
	String file = "";
	
	public void run() {
		JFrame frame = new JFrame("Activity Tracker");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));	
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(Color.gray);
		
		JPanel button = new JPanel();
        JButton CreateAccount = new JButton("Create Account");
        CreateAccount.setFont(new Font("Serif",Font.PLAIN,27));
        button.add(CreateAccount);
		
        JButton Login = new JButton("LogIn");
        Login.setFont(new Font("Serif",Font.PLAIN,27));
        
        button.add(Login);
		
		double avgTime;
		double avgDistance;
		double avgPace;
		double avgAltGained;
		double avgAltLost;
		
        
        JPanel textFields = new JPanel();
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
        JTextField usernameTextField = new JTextField(30);
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Serif",Font.PLAIN,27));
        usernameTextField.setFont(new Font("Serif",Font.PLAIN,27));
        JTextField passwordTextField = new JTextField(30);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Serif",Font.PLAIN,27));
        passwordTextField.setFont(new Font("Serif",Font.PLAIN,27));
        textFields.add(usernameLabel);
        textFields.add(usernameTextField, BorderLayout.NORTH);
        textFields.add(passwordLabel);
        textFields.add(passwordTextField, BorderLayout.SOUTH);
        
        JPanel loggedInView = new JPanel();
        loggedInView.setLayout(new BoxLayout(loggedInView, BoxLayout.Y_AXIS));
        loggedInView.setEnabled(false);
        JButton Synchronize = new JButton("Synchronize");
        Synchronize.setFont(new Font("Serif",Font.PLAIN,27));
        loggedInView.add(Synchronize, BorderLayout.NORTH);
        frame.add(loggedInView);
        JButton sortByNew = new JButton("Newest First");
        sortByNew.setFont(new Font("Serif",Font.PLAIN,27)); 
        JButton sortByDistance = new JButton("Farthest First");
        sortByDistance.setFont(new Font("Serif",Font.PLAIN,27));
        JButton findFile = new JButton("Find File");
        findFile.setFont(new Font("Serif",Font.PLAIN,27));
        
        findFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				JFileChooser chooser = new JFileChooser();
		        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        //  );
		        //chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            System.out.println("You chose to open this file: " +
		                    chooser.getSelectedFile().getName());
		            		file= chooser.getSelectedFile().getPath();
		        };
		        
					
					
					
					
					
					double avgTime =0;
					double avgDistance = 0;
					double avgPace = 0;
					double avgAltGained = 0;
					double avgAltLost = 0;
					try {
						String data[][] = ReadCSV.read(true, chooser.getSelectedFile().getPath());
						String[] headers = { "Time", "Distance", "Final Altitude", "Date", "Altitude Gained", "Altitude Lost" };
						String[][] headersList = {headers};
						JTable header = new JTable(headersList, headers);
						JTable dataTable = new JTable(data, headers);
						loggedInView.remove(findFile);
						loggedInView.remove(Synchronize);
						loggedInView.add(sortByDistance);
						loggedInView.add(header);
						loggedInView.add(dataTable);
						int j = 0;
						for(int i=0;i<data.length;i++) {
							avgTime += Double.parseDouble(data[i][0]);
							avgDistance +=Double.parseDouble(data[i][2]);
							avgAltGained += Double.parseDouble(data[i][4]);
							avgAltLost += Double.parseDouble(data[i][5]);
							j++;
						}
						avgTime = avgTime/j;
						avgDistance = avgDistance/j;
						avgAltGained = avgAltGained/j;
						avgAltLost = avgAltLost/j;
						avgPace = avgTime/avgDistance;
						
						String[][] averages = {{Double.toString(avgTime),Double.toString(avgDistance),Double.toString(avgAltGained),Double.toString(avgAltLost), Double.toString(avgPace)}};
						String[][] averagesHeader = {{"Average Time", "Average Distance", "Average Altitude Gained", "Average Altitude Lost", "Average Pace"}};
						JTable Averages = new JTable(averagesHeader, averagesHeader[0]);
						JTable averagesData = new JTable(averages, averagesHeader[0]);
						loggedInView.add(Averages);
						loggedInView.add(averagesData);
						loggedInView.revalidate();
						loggedInView.repaint();
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
					
		);
        
        CreateAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				createAccount(usernameTextField.getText(), passwordTextField.getText());
				if(logIn(usernameTextField.getText(), passwordTextField.getText())) {
					textFields.setVisible(false);
					button.setVisible(false);
					loggedInView.setEnabled(true);
					frame.add(loggedInView);
					loggedInView.add(Synchronize);
					frame.repaint();
				}else {
					usernameTextField.setText("");
				}
				
				
			}
					
		});
        //
        Login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				if(logIn(usernameTextField.getText(), passwordTextField.getText())) {
					textFields.setVisible(false);
					button.setVisible(false);
					loggedInView.setEnabled(true);
					frame.add(loggedInView);
					loggedInView.add(findFile);
					loggedInView.remove(Synchronize);
					loggedInView.revalidate();
					loggedInView.repaint();
					
				}else {
					usernameTextField.setText("");
				}
				
				
			}
					
		});
        
        Synchronize.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				
				
				
				
				
				double avgTime =0;
				double avgDistance = 0;
				double avgPace = 0;
				double avgAltGained = 0;
				double avgAltLost = 0;
				try {
					String data[][] = ReadCSV.read(true, file);
					String[] headers = { "Time", "Distance", "Final Altitude", "Date", "Altitude Gained", "Altitude Lost" };
					String[][] headersList = {headers};
					JTable header = new JTable(headersList, headers);
					JTable dataTable = new JTable(data, headers);
					loggedInView.remove(findFile);
					loggedInView.remove(Synchronize);
					loggedInView.add(sortByDistance);
					loggedInView.add(header);
					loggedInView.add(dataTable);
					int j = 0;
					for(int i=0;i<data.length;i++) {
						avgTime += Double.parseDouble(data[i][0]);
						avgDistance +=Double.parseDouble(data[i][2]);
						avgAltGained += Double.parseDouble(data[i][4]);
						avgAltLost += Double.parseDouble(data[i][5]);
						j++;
					}
					avgTime = avgTime/j;
					avgDistance = avgDistance/j;
					avgAltGained = avgAltGained/j;
					avgAltLost = avgAltLost/j;
					avgPace = avgTime/avgDistance;
					
					String[][] averages = {{Double.toString(avgTime),Double.toString(avgDistance),Double.toString(avgAltGained),Double.toString(avgAltLost), Double.toString(avgPace)}};
					String[][] averagesHeader = {{"Average Time", "Average Distance", "Average Altitude Gained", "Average Altitude Lost", "Average Pace"}};
					JTable Averages = new JTable(averagesHeader, averagesHeader[0]);
					JTable averagesData = new JTable(averages, averagesHeader[0]);
					loggedInView.add(Averages);
					loggedInView.add(averagesData);
					loggedInView.revalidate();
					loggedInView.repaint();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
					
		});
        
       
		
        
        sortByDistance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				try {
					
					double avgTime =0;
					double avgDistance = 0;
					double avgPace = 0;
					double avgAltGained = 0;
					double avgAltLost = 0;
					
					String data[][] = ReadCSV.read(false, file);
					String[] headers = { "Time", "Distance", "Final Altitude", "Date", "Altitude Gained", "Altitude Lost" };
					String[][] headersList = {headers};
					JTable header = new JTable(headersList, headers);
					JTable dataTable = new JTable(data, headers);
					loggedInView.removeAll();
					loggedInView.add(sortByNew);
					loggedInView.add(header);
					loggedInView.add(dataTable);
					
					int j = 0;
					for(int i=0;i<data.length;i++) {
						avgTime += Double.parseDouble(data[i][0]);
						avgDistance +=Double.parseDouble(data[i][2]);
						avgAltGained += Double.parseDouble(data[i][4]);
						avgAltLost += Double.parseDouble(data[i][5]);
						j++;
					}
					avgTime = avgTime/j;
					avgDistance = avgDistance/j;
					avgAltGained = avgAltGained/j;
					avgAltLost = avgAltLost/j;
					avgPace = avgTime/avgDistance;
					
					String[][] averages = {{Double.toString(avgTime),Double.toString(avgDistance),Double.toString(avgAltGained),Double.toString(avgAltLost), Double.toString(avgPace)}};
					String[][] averagesHeader = {{"Average Time", "Average Distance", "Average Altitude Gained", "Average Altitude Lost", "Average Pace"}};
					JTable Averages = new JTable(averagesHeader, averagesHeader[0]);
					JTable averagesData = new JTable(averages, averagesHeader[0]);
					loggedInView.add(Averages);
					loggedInView.add(averagesData);
					loggedInView.revalidate();
					loggedInView.repaint();
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
					
		});
        
        sortByNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				try {
					
					double avgTime =0;
					double avgDistance = 0;
					double avgPace = 0;
					double avgAltGained = 0;
					double avgAltLost = 0;
					
					
					String data[][] = ReadCSV.read(true, file);
					String[] headers = { "Time", "Distance", "Final Altitude", "Date", "Altitude Gained", "Altitude Lost" };
					String[][] headersList = {headers};
					JTable header = new JTable(headersList, headers);
					JTable dataTable = new JTable(data, headers);
					loggedInView.removeAll();
					loggedInView.add(sortByDistance);
					loggedInView.add(header);
					loggedInView.add(dataTable);
					loggedInView.revalidate();
					
					int j = 0;
					for(int i=0;i<data.length;i++) {
						avgTime += Double.parseDouble(data[i][0]);
						avgDistance +=Double.parseDouble(data[i][2]);
						avgAltGained += Double.parseDouble(data[i][4]);
						avgAltLost += Double.parseDouble(data[i][5]);
						j++;
					}
					avgTime = avgTime/j;
					avgDistance = avgDistance/j;
					avgAltGained = avgAltGained/j;
					avgAltLost = avgAltLost/j;
					avgPace = avgTime/avgDistance;
					
					String[][] averages = {{Double.toString(avgTime),Double.toString(avgDistance),Double.toString(avgAltGained),Double.toString(avgAltLost), Double.toString(avgPace)}};
					String[][] averagesHeader = {{"Average Time", "Average Distance", "Average Altitude Gained", "Average Altitude Lost", "Average Pace"}};
					JTable Averages = new JTable(averagesHeader, averagesHeader[0]);
					JTable averagesData = new JTable(averages, averagesHeader[0]);
					loggedInView.add(Averages);
					loggedInView.add(averagesData);
					loggedInView.revalidate();
					loggedInView.repaint();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
					
		});
        
        
        
        
        
        
        
        
        
        p.add(button, BorderLayout.NORTH);
        p.add(textFields, BorderLayout.CENTER);
		
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static boolean logIn(String username, String password) {
		Login logger = new Login(username, password);
		if (logger.verifyAccountDetails()) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public static void createAccount(String username, String password) {
		CreateAccount next = new CreateAccount(username , password,0,0,0);
	}
    
	
	
	



	}


