package com.example.productservice.controller;
import com.example.productservice.model.ProductRequestDto;
import com.example.productservice.model.ProductResponseDto;
import com.example.productservice.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto product = productService.createProduct(productRequestDto);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId, @RequestParam Long wantedQuantity){
        productService.reduceQuantity(productId, wantedQuantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") long productId) {
        ProductResponseDto productResponse
                = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }




}
