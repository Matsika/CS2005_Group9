

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import CreateAccount;
//import ReadCSV;

//test

public class loginUI {
	
	public static void main(String[]args) {
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
        frame.add(Synchronize);
        
        CreateAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				createAccount(usernameTextField.getText(), passwordTextField.getText());
			}
					
		});
        
        Login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				if(logIn(usernameTextField.getText(), passwordTextField.getText())) {
					System.out.println("here");
					textFields.setVisible(false);
					button.setVisible(false);
					loggedInView.setEnabled(true);
					frame.add(loggedInView);
					loggedInView.add(Synchronize);
					frame.repaint();
					
					ReadCSV reader = new ReadCSV();
					
				}
			}
					
		});
        
        Synchronize.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				ReadCSV reader = new ReadCSV();
				//List newData = reader.read();
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

