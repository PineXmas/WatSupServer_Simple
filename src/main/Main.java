package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
	static Random randomizer = new Random(System.currentTimeMillis());
	
	public static void main(String[] caiJCungDc) {
		System.out.println("Hello Ann");
		
		if (caiJCungDc.length <= 1) {
			System.out.println("Not enough args to start server");
			return;
		}
		
		int port = -1;
		
		try {
			port = new Integer(caiJCungDc[1]);
		} catch (NumberFormatException e) {
			port = randomizer.nextInt(50000) + 10000;
			System.out.println("Error. Use random port: " + port);
		}
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket= new ServerSocket(port);
			
			System.out.println("This server will only wait for one connection --> output the connected client's address --> send back \"Hello <client-address>\" --> quit!");
			clientSocket = serverSocket.accept();
			
			String clientAddress = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
			System.out.println("Connected to " + clientAddress);
			
			System.out.println("Send back \"Hello " + clientAddress + "\"...");
			String msg = "Hello " + clientAddress + "\n";
			clientSocket.getOutputStream().write(msg.getBytes());
			
			clientSocket.close();
			serverSocket.close();
			
			System.out.println("Done. Bye.");
		} catch (Exception e) {
			System.out.println("Error while running server. Try quitting server...");
		} finally {
			if (serverSocket != null) {
				if (!serverSocket.isClosed()) {
					try {
						serverSocket.close();
					} catch (IOException e) {
						System.out.println("Error while closing server socket!");
					}
				}
			}
			
			if (clientSocket != null) {
				if (!clientSocket.isClosed()) {
					try {
						clientSocket.close();
					} catch (IOException e) {
						System.out.println("Error while closing client socket!");
					}
				}
			}
		}
	}
}
