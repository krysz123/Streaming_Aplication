class StreamInteraction {
    private int likes;
    private int shares;

    public StreamInteraction(int likes, int shares) {
        this.likes = likes;
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

}