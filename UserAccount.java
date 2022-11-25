import java.util.Scanner;

public class UserAccount {
	private String username;
	private String password;
	public String Name;
	public String status;
	
	public void updateAccount() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter username: ");
		String user = in.nextLine();
		this.username = user;
		System.out.println("Enter password: ");
		String pass = in.nextLine();
		this.password = pass;
		in.close();
	}
	
	public void updateStatus() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter status: ");
		String stat = in.nextLine();
		this.status = stat;
		in.close();
	}
}
