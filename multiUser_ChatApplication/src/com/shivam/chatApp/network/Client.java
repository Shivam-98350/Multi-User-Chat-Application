package com.shivam.chatApp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.shivam.chatApp.DAO.UserDAO;
import com.shivam.chatApp.utils.ConfigReader;
import com.shivam.chatApp.utils.UserInfo;

public class Client {
	Socket socket; //SOCKET for client
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	static JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));	
		socket = new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		
		readMessages();
		
		/*SINGLE CLIENT*/
//		System.out.println("Client clubs");
//		System.out.println("Enter the message to be sent....");
//		Scanner scanner = new Scanner(System.in);
//		String message = scanner.nextLine(); //till the user presses enter, it will keep taking input
//		OutputStream out = socket.getOutputStream();//to write/send the bytes to the server
//		out.write(message.getBytes());
//		out.close();
//		socket.close();
	}
	
	public void sendMessage(String message) throws IOException {
		//bufferedReaader keeps reading untill it sees a \n
		//we manually concat \n with every message we send as a client
		if(!message.equals("") && message != null) { 
			message = message+"\n";
			out.write(message.getBytes());
			UserDAO.storeChatLog(UserInfo.USER_NAME, message);
		}
	}
	
	public void readMessages() { //calling a read thread
		worker = new ClientWorker(in, textArea);
		worker.start();
	}

	 public void closeSocket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//	public static void main(String[] args) throws UnknownHostException, IOException {
//		new Client(textArea);
//
//	}

}
