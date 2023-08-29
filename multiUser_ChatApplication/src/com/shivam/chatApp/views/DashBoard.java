package com.shivam.chatApp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class DashBoard extends JFrame {

	private JPanel contentPane;

	public DashBoard(String message) {
		setBackground(new Color(255, 255, 255));
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 555);
		
		JMenuBar chatMenuBar = new JMenuBar();
		chatMenuBar.setBackground(new Color(64, 224, 208));
		chatMenuBar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		setJMenuBar(chatMenuBar);
		
		JMenu chatMenu = new JMenu("Chat Options");
		chatMenu.setForeground(new Color(0, 0, 0));
		chatMenu.setHorizontalAlignment(SwingConstants.CENTER);
		chatMenu.setBackground(new Color(255, 255, 255));
		chatMenu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		chatMenuBar.add(chatMenu);
		
		JMenuItem startChat = new JMenuItem("Start Chat");
		startChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ClientChatScreen();
					setVisible(false);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		startChat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		startChat.setHorizontalAlignment(SwingConstants.CENTER);
		chatMenu.add(startChat);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setTitle(message);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/images/ChitChat.png")));
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
	}

}
