package servlet;

import entity.User;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService;



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        userService.saveUser(User.builder()
                .userId(UUID.randomUUID())
                .login(login)
                .password(password)
                .build());

    }
}
