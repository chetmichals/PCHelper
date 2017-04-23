package com.cmichals.PCHelper.WinApp;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

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
	
	private static String SERVICE_TYPE = "_json.tcp.local";
	private static String SERVICE_NAME = "PC_HELPER";
	private static String SERVICE_DESC = "Utility to interact with PC over network";
	
	public static void main(String arg[]) throws IOException{
		listen(CONNECTION_PORT_NUMBER);
	}
	
	@SuppressWarnings("resource")
	public static void listen(int portNumber) throws UnknownHostException, IOException{
		JsonParser parser = new JsonParser();
		JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
		ServerSocket serverSocket;
		ServiceInfo serviceInfo;
		
		//Create Socket
		try {
			serverSocket = new ServerSocket(portNumber, BACKLOG_SIZE);
		} catch (IOException e1) {
			throw new RuntimeException("Could not open socket");
		}
		
		//Register Service on Network
		serviceInfo = ServiceInfo.create(SERVICE_TYPE, SERVICE_NAME, serverSocket.getLocalPort(), SERVICE_DESC);
		jmdns.registerService(serviceInfo);
		
		//Read messages from the socket and route to the CommandExecutor 
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
