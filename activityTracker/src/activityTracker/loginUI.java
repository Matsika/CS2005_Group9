package activityTracker;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

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
        button.add(Login);
        
        
        p.add(button, BorderLayout.CENTER);
		
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
		
	}
  
        
	}

