package homework.chatserverhw;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoomServlet extends HttpServlet {
    private static final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomName = req.getParameter("room");
        if (roomName != null && !roomName.isEmpty()) {
            chatRooms.putIfAbsent(roomName, new ChatRoom(roomName));
            resp.setStatus(HttpServletResponse.SC_OK); // 200
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String json = new Gson().toJson(chatRooms.keySet());
        try (OutputStream os = resp.getOutputStream()) {
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}

