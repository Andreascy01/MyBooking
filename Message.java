public class Message {
    private String sender, receiver, text;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method to return the username of the message sender for use in MessageJList
     * @return sender
     */
    @Override
    public String toString() {
        return sender;
    }
}
