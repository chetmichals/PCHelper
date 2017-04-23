package com.cmichals.PCHelper.WinApp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class PortListener 
{
	public static final int CONNECTION_PORT_NUMBER = 21016;
	private static final int BACKLOG_SIZE = 1000;
	
	public static void main(String arg[]) throws IOException{
		listen(CONNECTION_PORT_NUMBER);
	}
	
	@SuppressWarnings("resource")
	public static void listen(int portNumber){
		JsonParser parser = new JsonParser();
		
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(portNumber, BACKLOG_SIZE);
		} catch (IOException e1) {
			throw new RuntimeException("Could not open socket");
		}
		while(true) {
			try {
				Socket client = serverSocket.accept();
				DataInputStream dis = new DataInputStream(client.getInputStream());
				try {
					while(client.isClosed() == false) {
						String inputData = dis.readUTF();
						JsonObject inputObject = parser.parse(inputData).getAsJsonObject();
						CommandExecutor.parseInput(inputObject);
					}
				} catch (EOFException e) {
					//Do nothing except break out of while loop
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
