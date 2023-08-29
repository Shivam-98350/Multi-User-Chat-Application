package com.shivam.chatApp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ClientWorker extends Thread{

	/*
	 * Every Client will use two Threads
	 * Sending the data from a Client is done by ServerWorker
	 * Reads the data coming to a Client is done by ClientWorker
	 */
	private InputStream in;
	private JTextArea textArea;
	public ClientWorker(InputStream in, JTextArea textArea) {
		this.in = in;
		this.textArea = textArea;
	}
	
	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while ((line = br.readLine()) != null) {
                System.out.println("Line read by Other Clients..." + line);
                appendToTextArea(line);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void appendToTextArea(String message) {
		 SwingUtilities.invokeLater(() -> {
	            textArea.append(message + "\n");
	        });
		
	}
}
