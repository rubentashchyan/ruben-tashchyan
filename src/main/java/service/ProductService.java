package service;

import dao.ProductDAO;
import entity.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;


    public void createProduct(Product product) throws IOException {
        productDAO.save(product);
    }
    public List<Product> getAllProducts() throws IOException {
        return productDAO.findAll();
    }

    public void deleteProduct(UUID productId) throws IOException {
        productDAO.delete(productId);
    }
}
