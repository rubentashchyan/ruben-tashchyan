package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductDAO;
import dao.UserDao;
import service.ProductService;
import service.UserService;
import service.validation.LoginValdation;
import service.validation.PasswordValidation;
import util.PasswordUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ObjectMapper objectMapper = new ObjectMapper();

        File userFile = new File( "C:\\product-list-servlets\\src\\main\\resources\\users.json" );
        File productFile = new File( "C:\\product-list-servlets\\src\\main\\resources\\products.json" );

        UserDao userDao = new UserDao(objectMapper, userFile);
        ProductDAO productDao = new ProductDAO(objectMapper, productFile);

        ProductService productService = new ProductService(productDao);
        UserService userService = new UserService(userDao);

        LoginValdation loginValdation = new LoginValdation(userService);
        PasswordValidation passwordValidation = new PasswordValidation();

        PasswordUtil passwordUtil = new PasswordUtil();

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("productService", productService);
        servletContext.setAttribute("passwordUtil", passwordUtil);
        servletContext.setAttribute("loginValdation", loginValdation);
        servletContext.setAttribute("passwordValidation", passwordValidation);
    }
}

