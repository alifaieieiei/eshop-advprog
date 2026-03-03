package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

// ISP
interface ProductCreateService {
    // SRP
    Product create(Product product);
}

// ISP
interface ProductReadService {
    // SRP
    List<Product> findAll();
    // SRP
    Product findById(String id);
}

// ISP
interface ProductUpdateService {
    // SRP
    Product update(Product product);
}

// ISP
interface ProductDeleteService {
    // SRP
    boolean delete(String productId);
}

// ISP, DIP
public interface ProductService
        extends ProductCreateService,
        ProductReadService,
        ProductUpdateService,
        ProductDeleteService {
}
