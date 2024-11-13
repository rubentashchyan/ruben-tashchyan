package servlet;

import entity.Product;
import service.ProductService;

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
import java.util.UUID;

@WebServlet("/secure/products")
public class ProductServlet extends HttpServlet {

    ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        List<Product> products = productService.getAllProducts();
        List <Product> userProducts = products.stream()
                .filter(product -> product.getUserid().equals(session.getAttribute("userId")))
                .toList();
        req.setAttribute("products", userProducts);
        req.getRequestDispatcher("/secure/products").forward(req, resp);
    }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String name = req.getParameter("name");
       String imageURL = req.getParameter("imageURL");
       HttpSession session = req.getSession();

       productService.createProduct(Product.builder()
               .id(UUID.randomUUID())
               .name(name)
               .imageurl(imageURL)
               .userid((UUID) session.getAttribute("userid"))
               .build());
       req.getRequestDispatcher("/secure/products").forward(req, resp);
   }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
