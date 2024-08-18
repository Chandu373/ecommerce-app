package com.chandu.product.service;

import com.chandu.product.domain.Product;
import com.chandu.product.exception.ProductNotFoundException;
import com.chandu.product.exception.ProductPurchaseException;
import com.chandu.product.mapper.ProductMapper;
import com.chandu.product.record.ProductPurchaseRequest;
import com.chandu.product.record.ProductPurchaseResponse;
import com.chandu.product.record.ProductRequest;
import com.chandu.product.record.ProductResponse;
import com.chandu.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    private static  final Logger log = LoggerFactory.getLogger(ProductService.class);


    public Product save(ProductRequest productRequest) {
        if(productRequest == null){
            return  null;
        }
        Product product = productMapper.toProduct(productRequest);
        return product;
    }

    public void deleteProduct(Integer productId) {
       productRepository.deleteById(productId);
    }


    public List<ProductResponse> findAllProducts() {
       return  productRepository.findAll().stream()
               .map(productMapper::fromProduct)
               .collect(Collectors.toList());
    }

    public ProductResponse findProductById(Integer productId) {
        return productRepository.findById(productId).map(productMapper::fromProduct)
                .orElseThrow(()-> new ProductNotFoundException(
                        String.format("product not  found  for this  ID :%s",productId)));
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = new ArrayList<>();
        List<Product> storedProducts = new ArrayList<>();
        List<ProductPurchaseRequest> storedRequest = new ArrayList<>();
        List<ProductPurchaseResponse> purchaseProducts = new ArrayList<>();

        productIds = request.stream()
                .map(ProductPurchaseRequest::productId).toList();
        storedProducts = null ; //productRepository.findAllProductOrderById(productIds);

        if(productIds.size() != storedProducts.size()){
            log.info("One or more products not exist");
            throw  new ProductPurchaseException("One or more products not exist");
        }
        storedRequest = request.stream().
                sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();


        for(int i =0 ; i < storedProducts.size() ;i++){
            Product product =  storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                log.info("Insufficient stock for product with ID ::%s{}", productRequest.productId());
                throw  new ProductPurchaseException("Insufficient stock for product with ID ::"+productRequest.productId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }


        return  purchaseProducts;
    }
}
