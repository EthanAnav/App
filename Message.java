public class Message {
	public String Sender;
	public String Recipient;
	public String[] content;
	
	public void send() {
		for (int i = 0; i < content.length; i++) {
			System.out.println(content[i]);
		}
	}
}
