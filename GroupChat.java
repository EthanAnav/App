public class GroupChat {
	public String groupID;
	public String groupName;
	private Message Message;
	private ChatHistory Chat;
	
	public void getMessage() {}
	
	public void setMessage() {}
	
	public boolean login() {
		return false;
	}
	
	public boolean logout() {
		return false;
	}
	
	public String search(String text) {
		for (int i = 0; i < Chat.messageList.length; i++) {
			if (Chat.messageList[i] == text) {
				return Chat.messageList[i];
			}
		}
		return null;
	}
}
