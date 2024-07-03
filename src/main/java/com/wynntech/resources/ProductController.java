package com.wynntech.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import com.wynntech.model.Product;
import com.wynntech.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            String productId = productService.createProduct(product);
            return ResponseEntity.ok(productId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    // Bulk Create Products
//    @PostMapping("/bulk")
//    public ResponseEntity<List<String>> bulkCreateProducts(@RequestBody List<Product> products) {
//        try {
//            List<String> productIds = productService.bulkCreateProducts(products);
//            return ResponseEntity.ok(productIds);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        try {
            Product product = productService.getProduct(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            product.setId(id);
            String updatedProductId = productService.updateProduct(product);
            return ResponseEntity.ok(updatedProductId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        try {
            String deletedProductId = productService.deleteProduct(id);
            return ResponseEntity.ok(deletedProductId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    // Search Products
//    @GetMapping("/search")
//    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
//        try {
//            List<Product> products = productService.searchProducts(keyword, page, size);
//            return ResponseEntity.ok(products);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
}