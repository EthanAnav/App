import java.util.List;
import java.util.Scanner;

public class UserAccount {
	private User user = new User(null, null, null);
	public ContactList list;
	
	
	 public void login(String username, String password, String status) {
		 	Scanner in = new Scanner(System.in);
			System.out.println("Enter username: ");
			user.setUsername(in.nextLine());
			System.out.println("Enter password: ");
			user.setPassword(in.nextLine());
			in.close();
			
	        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	        	user.setStatus("logged in"); 
	        	list.contacts.add(user);
	        } else {
	        	user.setStatus("logged out"); 
	        }
	    }

	    public void logout() {
	       user.setStatus("logged out");
	    }

}
