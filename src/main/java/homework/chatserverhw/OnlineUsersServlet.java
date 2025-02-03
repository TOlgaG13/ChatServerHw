package homework.chatserverhw;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineUsersServlet extends HttpServlet {
    private static final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String json = new Gson().toJson(onlineUsers);
        try (OutputStream os = resp.getOutputStream()) {
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }

    public static void addUser(String user) {
        onlineUsers.add(user);
    }

    public static void removeUser(String user) {
        onlineUsers.remove(user);
    }
    public static boolean isUserOnline(String user) {
        return onlineUsers.contains(user);
    }
}
