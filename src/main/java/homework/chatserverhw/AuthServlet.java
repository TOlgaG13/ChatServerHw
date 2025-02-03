package homework.chatserverhw;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthServlet  extends HttpServlet {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (users.containsKey(login) && users.get(login).equals(password)) {
            resp.setStatus(HttpServletResponse.SC_OK); // 200
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        }
    }
}

