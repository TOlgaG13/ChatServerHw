package homework.chatserverhw;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private final List<Message> list = new LinkedList<>();
    private final List<Message> privateMessages = new LinkedList<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized void addPrivateMessage(Message m) {
        privateMessages.add(m);
    }

    public synchronized String toJSON(int n) {
        if (n < 0 || n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, n));
    }

    public synchronized String toPrivateJSON(int n) {
        if (n < 0 || n >= privateMessages.size()) return null;
        return gson.toJson(new JsonMessages(privateMessages, n));
    }
}

