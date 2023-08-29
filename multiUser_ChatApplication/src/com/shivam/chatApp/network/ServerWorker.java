package com.shivam.chatApp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.shivam.chatApp.utils.UserInfo;

//Thread == Worker
//Every Thread requires a job to perform 
//And to execute the job, it requires a Runnable 
//Once job is created via Runnable, write the job login inside a 'run' function
//then assign the job to a thread

public class ServerWorker extends Thread{
	private Socket clientSocket;
	//Per client, per InputStream & OutputStream
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); //Client Data Read -> Data Aayega
		out = clientSocket.getOutputStream(); //Client Data Write -> Data Jayega
		System.out.println("New Client comes...");
	}
	@Override
	public void run() {
		//Read data from the client and broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while ((line = br.readLine()) != null)
              {
				//data is coming continuously
				System.out.println("Line read by Server..."+line);
				
				if(line.equalsIgnoreCase("quit")) {
					
//					System.out.println("inside if of quit");
//					System.out.println(server.workers);
////					
//					line ="Left"+"\n";
//					for(ServerWorker serverWorker : server.workers) {
//						serverWorker.out.write(line.getBytes()); //ek ek karke sab ko bheje ga message
//					}
					server.workers.remove(this);
//					System.out.println(server.workers);
					
//					if(clientSocket!=null) {
//						clientSocket.close();
//						System.out.println("socket closed");
//					}
//					System.exit(0);
					break;//we will stop that particular client and not take its data
					//Client Chat End
				}
				//out.write(line.getBytes());//Client sends ->this will send it only to the Server
				
				//we want to BROADCAST the message to all the Clients via Server Worker Threads
				if(line !=null) {
					line = line+"\n";
				for(ServerWorker serverWorker : server.workers) {
					
					serverWorker.out.write(line.getBytes()); //ek ek karke sab ko bheje ga message
					}
				}
			}
			
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(br!=null) {
					br.close();
				}
				if(in!=null) {
					in.close();
				}
				if(out!=null) {
					out.close();
				}
				if(clientSocket!=null) {
					clientSocket.close();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}















//LEARNING MULTITHREADING

//public class ServerWorker implements Runnable{

//public class ServerWorker extends Thread{
//
//	@Override
//	public void run() {
//		//Job to Perform
//		for(int i=0;i<=5;i++) {
//			System.out.println("RUN i is "+i+" "+Thread.currentThread());
//		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public static void main(String[] args) throws InterruptedException {
//		//Main is a thread itself
//		
//		// Every thread has its own stack 
//		//so they can run parallely
//		//ex. if we run loop in main() and run a loop in run(), they will run parallely
//		
//		//ServerWorker job = new ServerWorker();
//		//assign a job to a thread
////		Thread worker = new Thread(job,"worker1"); // worker1 is the name of the thread
////		worker.start(); //this calls the run() method internally
//		//Thread.sleep(1000);
//		
//		ServerWorker worker = new ServerWorker(); //we dont have to create the object of Thread now
//		//when we extend Thread, Thread already implements Runnable Interface
//		worker.start(); //it internally calls run() method
//		
//		
//		for(int j =1;j<=5;j++) {
//			System.out.println("Main "+j+" "+Thread.currentThread());
//		}
//	}
//
//}






