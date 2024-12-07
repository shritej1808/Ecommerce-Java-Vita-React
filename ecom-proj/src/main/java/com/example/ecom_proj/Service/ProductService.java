package com.example.ecom_proj.Service;

import com.example.ecom_proj.Model.Product;
import com.example.ecom_proj.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository pr;

    public List<Product> getAllproducts() {
        return pr.findAll();
    }

    public Product addProducts(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return pr.save(product);

    }

    public Product getProductById(int id) {
        return pr.findById(id).get();
    }

    public void deleteProduct(int id) {
        pr.deleteById(id);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return pr.save(product);
    }

    public List<Product> searchProducts(String keyword) {
        return pr.searchProducts(keyword);
    }
}
















