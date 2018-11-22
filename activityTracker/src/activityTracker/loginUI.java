package activityTracker;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import activityTracker.CreateAccount;
import activityTracker.ReadCSV;

//test

public class loginUI  {
	
	public static void main(String[]args) {
		JFrame frame = new JFrame("Activity Tracker");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));	
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(Color.CYAN);
		
		JPanel button = new JPanel();
        JButton CreateAccount = new JButton("Create Account");
        CreateAccount.setFont(new Font("Serif",Font.PLAIN,27));
        
        button.add(CreateAccount);
        JButton Login = new JButton("LogIn");
        Login.setFont(new Font("Serif",Font.PLAIN,27));
        
        Login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				logIn();
			}
					
		});
        
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
        
        CreateAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click) {
				createAccount(usernameTextField.getText(), passwordTextField.getText());
			}
					
		});
        
        
        
        p.add(button, BorderLayout.NORTH);
        p.add(textFields, BorderLayout.CENTER);
		
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void logIn() {
		int i = 6;
	}
	
	
	
	public static void createAccount(String username, String password) {
		CreateAccount next = new CreateAccount(username , password,0,0,0);
		System.out.println(next.getUserName());
	}
        
}

