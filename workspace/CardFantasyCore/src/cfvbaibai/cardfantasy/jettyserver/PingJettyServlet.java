package cfvbaibai.cardfantasy.jettyserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PingJettyServlet extends HttpServlet {

    private static final long serialVersionUID = -3363648548149688292L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("OK");
    }
}
