package dao;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDAO {

    private final ObjectMapper objectMapper;
    private final File file;

    public void save(Product product) throws IOException {
        try {  List<Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>() {}
        );
        } catch  (IOException e) {
            throw new RuntimeException(e);}
    }

    public List<Product> findAll() {
        try { return objectMapper.readValue(file, new TypeReference<List<Product>>() {
        });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID productId) {
        try {
            List <Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>() {}
            );
            List <Product> productList = products.stream().filter(product -> !product.getProductId().equals(productId)).toList();
            objectMapper.writeValue(file, productList);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
