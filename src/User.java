public class User {

    private String username;
    private UserType userType;
    private boolean isBlocked;

    public User(String username) {
        this.username = username;
        this.userType = userType.USER;
        this.isBlocked = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}



