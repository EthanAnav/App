import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
	static Vector<ActiveUsers> active = new Vector<>();
	//counts the clients
	static int i = 0;
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7777);
		Socket socket;
		
		while(true) {
			socket = server.accept();
			System.out.println("New user connected: " + socket);
			
			ObjectInputStream in  = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("Creating a new thread for this client...");
			
			ActiveUsers user = new ActiveUsers(socket,"client " + i, in, out);
			
			Thread newUser = new Thread(user);
			
			active.add(user);
			
			newUser.start();
			
			i++;
		}
	}
}

class ActiveUsers implements Runnable {
	Scanner scn = new Scanner(System.in);
	private String name;
	final ObjectInputStream finalIn;
    final ObjectOutputStream finalOut;
	Socket s;
	boolean isloggedin; 
	
	public ActiveUsers(Socket s, String name, ObjectInputStream finalIn, ObjectOutputStream finalOut) {
		this.finalIn = finalIn;
		this.finalOut = finalOut;
		this.name = name;
		this.s = s;
		this.isloggedin=true;
	}
	
	@Override
	public void run() {
		String notification;
		while(true) {
			try {
				notification = finalIn.readUTF();
				System.out.println(notification);
				
				for (ActiveUsers mc : Server.active)
				{
				// if the recipient is found, write on its
				// output stream
				if (mc.name.equals(user) && mc.isloggedin==true)
				{
				mc.finalOut.writeUTF(this.name+" : "+ Message);
					break;
				}
				
				this.finalIn.close();
				this.finalOut.close();
			}
			
			catch(IOException e){
				e.printStackTrace();
		    }
		}
		
	}
	
	
	
