package com.shivam.chatApp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shivam.chatApp.network.Client;
import com.shivam.chatApp.utils.UserInfo;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;
	JButton sendIt;
	JButton closeBtn;

	public static void main(String[] args) {
		try {
			ClientChatScreen frame = new ClientChatScreen();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendIt() {
		String message = textField.getText(); //whatever we type in the textField as a client
		textField.setText("");
		if(message.equalsIgnoreCase("quit"))
		{
			try {
				client.sendMessage(UserInfo.USER_NAME+":- LEFT");
				client.sendMessage("quit");
				sendIt.setEnabled(false);
				closeBtn.setEnabled(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        // Close the client socket
		        client.closeSocket();

		        // Dispose the JFrame
		        dispose();
				  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		try {
			client.sendMessage(UserInfo.USER_NAME+":- "+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public ClientChatScreen() throws UnknownHostException, IOException {
		setBackground(new Color(255, 255, 255));
		textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 0));
		client = new Client(textArea);
		setTitle(UserInfo.USER_NAME+"  Chit Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 634, 326);
		contentPane.add(scrollPane);
		
		
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setBounds(23, 22, 600, 292);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 102));
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(10, 348, 520, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 sendIt = new JButton("Send \r\nMessage");
		 sendIt.setForeground(new Color(0, 0, 0));
		sendIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendIt.setBackground(new Color(255, 204, 0));
		sendIt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		sendIt.setBounds(540, 348, 106, 37);
		contentPane.add(sendIt);
		
		 closeBtn = new JButton("CLOSE");
		 closeBtn.setBackground(new Color(255, 0, 0));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(UserInfo.USER_NAME+" left the chat");
				try {
					client.sendMessage(UserInfo.USER_NAME+":- LEFT");
					client.sendMessage("quit");
					sendIt.setEnabled(false);
					closeBtn.setEnabled(false);
					  try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				        // Close the client socket
				        client.closeSocket();

				        // Dispose the JFrame
				        dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				dispose();
//				System.exit(0);
			}
		});
		closeBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		closeBtn.setBounds(276, 394, 89, 23);
		contentPane.add(closeBtn);
		
		setVisible(true);
	}
}
