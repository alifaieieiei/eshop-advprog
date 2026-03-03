package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
// SRP
public class ProductServiceImpl implements ProductService {

    // DIP
    @Autowired
    private ProductRepository productRepository;

    @Override
    // SRP
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    // SRP
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    // SRP
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    // SRP
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    // SRP
    public boolean delete(String productId) {
        return productRepository.delete(productId);
    }
}

