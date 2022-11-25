
public class User {

    private String username;
    private String password;
    public String name;
    public String status;
    //public IndividualChat newIndividualChat;
    //public GroupChat newGroupChat;

    public void login(String username, String password) {
        if (this.username.equals(name) && this.password.equals(password)) {
            status = "login";
        } else {
            status = "logout";
        }
    }

    public void logout() {
        status = "logout";
    }
}
