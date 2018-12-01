package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
	static Random randomizer = new Random(System.currentTimeMillis());
	
	public static void main(String[] caiJCungDc) {
		int port = randomizer.nextInt(50000) + 10000;
		
		
		try {
			if (caiJCungDc.length > 0) {
				port = new Integer(caiJCungDc[0]);
			}
		} catch (NumberFormatException e) {
			port = randomizer.nextInt(50000) + 10000;
		}
		System.out.println("Use port: " + port);
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket= new ServerSocket(port);
			
			System.out.println("This server will only wait for one connection --> output the connected client's address --> send back \"Hello@Pine <client-address>\" --> quit!");
			clientSocket = serverSocket.accept();
			
			String clientAddress = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
			System.out.println("Connected to " + clientAddress);
			
			System.out.println("Send back \"Hello@Pine " + clientAddress + "\"...");
			String msg = "Hello@Pine " + clientAddress + "\n";
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
