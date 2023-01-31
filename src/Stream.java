import java.net.URL;
import java.util.ArrayList;

class Stream {
    private String title;
    private String description;
    private User author;
    private int viewerCount;
    private ArrayList<StreamChatMessage> messages;
    private StreamInteraction interactions;
    private String imageURl;

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public ArrayList<StreamChatMessage> getMessages() {
        return messages;
    }

    public int likeStream() {
        interactions.setLikes(interactions.getLikes()+1);
        return interactions.getLikes();
    }

    public int unlikeStream() {
        interactions.setLikes(interactions.getLikes()-1);
        return interactions.getLikes();
    }

    public int shareStream() {
        interactions.setShares(interactions.getShares()+1);
        return interactions.getShares();
    }

    public void setMessages(ArrayList<StreamChatMessage> messages) {
        this.messages = messages;
    }

    public StreamInteraction getInteractions() {
        return interactions;
    }

    public void setInteractions(StreamInteraction interactions) {
        this.interactions = interactions;
    }

    public Stream(String title, String description, User author, int viewerCount, StreamInteraction interactions,String url) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.viewerCount = viewerCount;
        this.interactions = interactions;
        this.imageURl=url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(int viewerCount) {
        this.viewerCount = viewerCount;
    }
}