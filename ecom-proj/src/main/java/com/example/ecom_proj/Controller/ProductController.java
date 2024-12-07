package com.example.ecom_proj.Controller;

import com.example.ecom_proj.Model.Product;
import com.example.ecom_proj.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService prservice;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = prservice.getAllproducts();
        products.forEach(product -> System.out.println(product)); // Log product details
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<?> addProducts(@RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) {
        try{
            Product product1=prservice.addProducts(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);


        }
        catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        return new ResponseEntity<>(prservice.getProductById(id),HttpStatus.OK );
    }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int productId){

            Product product=prservice.getProductById(productId);
            byte[] imageFile=product.getImageData();
            return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);

    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Product product=prservice.getProductById(id);
        if(product!=null){
            prservice.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
return new ResponseEntity<>("Unsuccessfull",HttpStatus.NOT_FOUND);
    }
@PutMapping("product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile){
            Product product1=null;
            try{
                product1=prservice.updateProduct(id,product ,imageFile);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(product1!=null){
                return new ResponseEntity<>("Updated",HttpStatus.OK);
            }
            return new ResponseEntity<>("Not able to find product",HttpStatus.NOT_FOUND);

}
@GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword ){
        List<Product> products=prservice.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
}
}
