import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	private String username;
	private String server;
	private int portNumber;
	//private User user;
	
	public static ArrayList <String> ActiveUsers;
	public static ArrayList <String> GroupChat;
	
	public void setUsername() {
		this.username = username;
	}
	
	public  String getUsername() {
		return username;
	}
	
	//For threaded client
	Client(String server, int portNumber, String username){
		this.server = server;
	    this.portNumber = portNumber;
	    this.username = username;
	}		
	
	
	public void run() {
		try {
			in  = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			while (true) {
				String message = in.readLine();
				if(message.equalsIgnoreCase("exit")) {
					break;
				}
				sendMessage(message);
				System.out.println("Server intercepted " + message);
			}
		}
		catch (Exception e) {
			System.out.println("Error occurred " + e.getStackTrace());
		}
		// creates the Thread to listen from the server 
		Listener listen = new Listener();
		Thread client = new Thread(listen);
		client.start();
	}
	
	void sendMessage(Message message) {
		try {
			out.writeObject(message);
		}
		catch(IOException e) {
			System.out.println("Error sending message " + e);
		}
	}
	
	public static void main(String[] args) {
		int portNumber = 7777;
		String server = "localhost";
		String userName = "miguel";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the username: ");
		userName = scan.nextLine();	
		
		// create the Client object
		Client client = new Client(server, portNumber, userName);
		
		while(true) {
			//GUI OPTIONS HERE
			break;
		}
		
		scan.close();
		
	}		
	


public void start() {
	while(true) {
		try {
			String message = in.readObject().toString(); 
			// print the message
			System.out.println(message);
			
		}
		catch(IOException e) {
			System.out.println("Server error " + e );
			break;
		}
		catch(ClassNotFoundException i) {
		}
	}
}
}



