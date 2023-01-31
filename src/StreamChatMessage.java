import java.time.LocalDateTime;

public class StreamChatMessage {

    private User user;
    private String message;
    private Time time;

    public StreamChatMessage(User user, String message,Time time) {
        this.time = time;
        this.user = user;
        this.message = message;
    }

    public StreamChatMessage( User user, String message) {
        LocalDateTime date = LocalDateTime.now();
        time=new Time(date.getHour(),date.getMinute(),date.getSecond());
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
