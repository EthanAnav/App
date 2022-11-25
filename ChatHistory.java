public class ChatHistory {
	public String sender;
	String[] messageList;
	
	public String[] getMessageList() {
		return messageList;
	}
	
	void clearChat() {
		for (int i = 0; i < messageList.length; i++) {
			messageList[i] = null;
		}
	}	
}
