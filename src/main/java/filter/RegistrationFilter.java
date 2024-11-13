package filter;

import service.validation.LoginValdation;
import service.validation.PasswordValidation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter("/registration")
public class RegistrationFilter implements Filter {

    private LoginValdation loginValdation;
    private PasswordValidation passwordValidation;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginValdation = (LoginValdation)filterConfig.getServletContext().getAttribute("loginvalidation");
        passwordValidation = (PasswordValidation)filterConfig.getServletContext().getAttribute("passwordvalidation");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getMethod().equals("POST")) {

            String login = request.getParameter("login");
            String password = request.getParameter("password");

            List<String> loginErrors = loginValdation.validate(login);
            List<String> passwordErrors = passwordValidation.validate(password);

            if (loginErrors.isEmpty() || !passwordErrors.isEmpty()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                if (!loginErrors.isEmpty()) {
                    request.setAttribute("loginErrors", loginErrors);
                }
                if (!passwordErrors.isEmpty()) {
                    request.setAttribute("passwordErrors", passwordErrors);
                }
                request.getRequestDispatcher("/registration.jsp").forward(request, servletResponse);
            }
        }

    }
}
