public class IndividualChat {
	private ContactList Contact;
	private Message Message;
	ChatHistory Chat;
	
	public void getMessage() {
		for (int i = 0; i < Message.content.length; i++) {
			System.out.println(Message.content[i]);
		}
	}
	
	public void setMessage() {}
	
	public void getNotification() {}
}
