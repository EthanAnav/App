import java.util.Scanner;

public class AddUser {
	private String username;
	private String password;
	public String name;
	
	public void createAccount() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter username: ");
		String user = in.nextLine();
		this.username = user;
		System.out.println("Enter password: ");
		String pass = in.nextLine();
		this.password = pass;
		System.out.println("Enter your name: ");
		String n = in.nextLine();
		this.name = n;
		in.close();
	}
	
	public void logout() {}
	
	public void login(String username, String password) {
		while ((username != this.username) || (password != this.password)) {
			System.out.println("Login unsuccessful, please try again.");
			Scanner in = new Scanner(System.in);
			System.out.println("Enter username: ");
			username = in.nextLine();
			System.out.println("Enter password: ");
			password = in.nextLine();
			in.close();
		}
		System.out.println("Login successful");
	}
}