package servlet;

import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;
import util.PasswordUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private PasswordUtil passwordUtil;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        String password =servletContext.getInitParameter("hashespassword");

    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           req.getRequestDispatcher("/login").forward(req,resp);

    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        BCrypt.hashpw(password, BCrypt.gensalt());
        String hashedPassword = PasswordUtil.generatePassword(password);
        PasswordUtil.checkPassword(password,hashedPassword);

        List<User> users = userService.getAllUsers();
        Optional<User> existedUser = users.stream().filter(user -> user.getLogin().equals(login))
        .filter(user -> PasswordUtil.checkPassword(hashedPassword, user.getPassword()))
                .findFirst();
        if (existedUser.isPresent()) {
            session.setAttribute("userId", existedUser.get().getUserId().toString());
            resp.sendRedirect("/secure/products");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }



}
