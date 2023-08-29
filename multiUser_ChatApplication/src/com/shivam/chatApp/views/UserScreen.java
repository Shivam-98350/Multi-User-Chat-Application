package com.shivam.chatApp.views;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.shivam.chatApp.DAO.UserDAO;
import com.shivam.chatApp.DTO.UserDTO;
import com.shivam.chatApp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField usertxt;
	private JPasswordField pwdtxt;

	public static void main(String[] args) {
		UserScreen window = new UserScreen();
	}
	
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid = usertxt.getText();
		char[] password = pwdtxt.getPassword(); //if we print this, it will give ClassName@HashCode(Hexadecimal form)
		//.getPassword() is a secure way than .getText()
		
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message="";
			if(userDAO.isLogin(userDTO)) {
				message = "Welcome "+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose(); //finishes from the memory as well
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}else {
				message = "Invalid UserId or Password...";
				JOptionPane.showMessageDialog(this, message);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void register() {
		String userid = usertxt.getText();
		char[] password = pwdtxt.getPassword(); //if we print this, it will give ClassName@HashCode(Hexadecimal form)
		//.getPassword() is a secure way than .getText()
		
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			int result = userDAO.add(userDTO);
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "Registered Successfully");
				//System.out.println("Record Added");
			} else {
				JOptionPane.showMessageDialog(this, "Registeration Failed");
				//System.out.println("Record NOT Added");
			}
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("DB Isssue...");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Some Generic Exception raised");
			e.printStackTrace();
		}
		
	}
	

	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(new Color(255, 215, 0));
		getContentPane().setLayout(null);
		
		JLabel headinglbl = new JLabel("Login");
		headinglbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
		headinglbl.setHorizontalAlignment(SwingConstants.CENTER);
		headinglbl.setBounds(185, 48, 139, 47);
		getContentPane().add(headinglbl);
		
		usertxt = new JTextField();
		usertxt.setForeground(new Color(0, 255, 0));
		usertxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		usertxt.setBounds(236, 121, 202, 38);
		getContentPane().add(usertxt);
		usertxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("User Id : ");
		useridlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		useridlbl.setBounds(103, 121, 92, 38);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password : ");
		pwdlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		pwdlbl.setBounds(103, 182, 123, 38);
		getContentPane().add(pwdlbl);
		
		pwdtxt = new JPasswordField();
		pwdtxt.setForeground(new Color(0, 250, 154));
		pwdtxt.setBackground(new Color(255, 255, 255));
		pwdtxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pwdtxt.setBounds(236, 183, 202, 37);
		getContentPane().add(pwdtxt);
		
		JButton registerbt = new JButton("Register");
		registerbt.setBackground(new Color(0, 0, 255));
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		registerbt.setBounds(256, 258, 98, 23);
		getContentPane().add(registerbt);
		
		JButton loginbt_1 = new JButton("Login");
		loginbt_1.setBackground(new Color(0, 255, 0));
		loginbt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		loginbt_1.setBounds(144, 258, 89, 23);
		getContentPane().add(loginbt_1);
		setSize(526, 383);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
