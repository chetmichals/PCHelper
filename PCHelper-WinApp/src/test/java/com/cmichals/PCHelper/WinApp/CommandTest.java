package com.cmichals.PCHelper.WinApp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommandTest {
	
	public static void main(String arg[]) throws UnknownHostException, IOException {
		//new Thread(() -> PortListener.listen(PortListener.CONNECTION_PORT_NUMBER)).start();
		
		
		Socket socket = new Socket("localhost", PortListener.CONNECTION_PORT_NUMBER);
		OutputStream socketOut = socket.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(socketOut);
		dOut.writeUTF("{'command':'windowForward'}");
		dOut.flush();
		socketOut.flush();
		dOut.writeUTF("{'command':'windowForward'}");
		dOut.flush();
		socketOut.flush();
		dOut.writeUTF("{'command':'selectWindow'}");
		dOut.flush();
		socketOut.flush();
        socket.close();
        System.exit(0);
	}

}
