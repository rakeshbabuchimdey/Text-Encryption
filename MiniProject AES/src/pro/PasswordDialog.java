package pro;

import javax.swing.JFrame; 
import javax.swing.JOptionPane; 
import javax.swing.JTextField; 
import javax.swing.JTextArea; 
import javax.swing.JPasswordField; 
import javax.swing.JPanel; 
import javax.swing.JLabel; 
import java.awt.GridLayout; 
import java.util.Arrays; 
import java.awt.EventQueue;
public class PasswordDialog
{ 
	public static String password(int i) 
	{ 
		JFrame guiFrame = new JFrame(); 
		JPanel userPanel = new JPanel(); 
		userPanel.setLayout(new GridLayout(2,2)); 
		if(i==0) 
			{ 
				JLabel passwordLbl = new JLabel("Enter Key to Encrypt:"); 
				userPanel.add(passwordLbl); 
			} 
			else
				{
				 	JLabel passwordLbl = new JLabel("Enter Key to Decrypt:"); 
				 	userPanel.add(passwordLbl); 
				 } 
				 JPasswordField passwordFld = new JPasswordField(); 
				 userPanel.add(passwordFld); 
				 int input = JOptionPane.showConfirmDialog(guiFrame, userPanel, "AES Key",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 
				 String enteredPassword =new String(passwordFld.getPassword()); 
				 return enteredPassword; 
	} 
} 