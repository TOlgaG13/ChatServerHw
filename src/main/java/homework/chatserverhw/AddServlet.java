package homework.chatserverhw;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AddServlet  extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException { // Apache commons-io
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req); // json
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        if (msg != null) {
            if (msg.getTo() != null && !msg.getTo().isEmpty()) {
                // Приватное сообщение
                msgList.addPrivateMessage(msg);
            } else {
                // Публичное сообщение
                msgList.add(msg);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
        }
    }
}

