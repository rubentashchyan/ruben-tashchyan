package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

@WebFilter("/*")
public class MethodFilter implements Filter {
    public final List<String> httpMethods = List.of("Delete", "PUT", "PUCH");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getParameter("_method");

        if (httpMethods.contains(method)) {
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
                @Override
                public String getMethod() {
                    return method;

                }


            };

            filterChain.doFilter(requestWrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}