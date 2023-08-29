package com.shivam.chatApp.network;

//import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.shivam.chatApp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket; //to create a server machine socket
	ArrayList<ServerWorker> workers = new ArrayList<>(); //contains all the client sockets
	
	public Server() throws IOException {
		//System.out.println("constructor");
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		System.out.println("Server start & waiting for clients...");
		serverSocket = new ServerSocket(PORT);
		handleClientRequest();
	}
	/*MULTIPLE CLIENT Handshaking*/
	public void handleClientRequest() throws IOException {
		//System.out.println("handleClientRequest");
		while (true) {
			Socket clientSocket = serverSocket.accept(); //handshaking -> it accepts the client
			//Per client per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket,this); //creating a new worker/thread
			// 'this' will pass the Server instance
			workers.add(serverWorker);
			serverWorker.start();
		}
	}
	
	/* SINGLE CLIENT 
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));	
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started. Waiting for client connection");
		Socket socket = serverSocket.accept(); //handshaking -> it accepts the client
		System.out.println("Client Joins the Server");
		InputStream in = socket.getInputStream(); //read bytes from the client on the network
		//byte arr[] = in.readAllBytes();
		
		byte[] arr = IOUtils.toByteArray(in);
		
//		byte[] arr = new byte[in.available()];
//		DataInputStream dataInputStream = new DataInputStream(in);
//		dataInputStream.readFully(arr);
		
		
		String str = new String(arr);//convert into string
		System.out.println("Message from client: "+str);
		in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		//System.out.println("Server ");
		Server server = new Server();
	}

}
