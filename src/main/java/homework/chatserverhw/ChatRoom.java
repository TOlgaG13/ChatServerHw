package homework.chatserverhw;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private final String name;
    private final List<Message> messages = new ArrayList<>();

    public ChatRoom(String name) {
        this.name = name;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }
}

