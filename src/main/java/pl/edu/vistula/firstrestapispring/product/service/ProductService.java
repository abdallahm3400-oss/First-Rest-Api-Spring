package pl.edu.vistula.firstrestapispring.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.domain.Product;
import pl.edu.vistula.firstrestapispring.product.repository.ProductRepository;
import pl.edu.vistula.firstrestapispring.product.support.ProductExceptionSupplier;
import pl.edu.vistula.firstrestapispring.product.support.ProductMapper;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository ProductRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository ProductRepository, ProductMapper productMapper) {
        this.ProductRepository = ProductRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse create(ProductRequest productRequest) {
        Product product = ProductRepository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(product);
    }
    public ProductResponse find(Long id) {
        Product product = ProductRepository.findById(id)
                .orElseThrow(ProductExceptionSupplier.productNotFound(id));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) {
        Product product = ProductRepository.findById(id)
                .orElseThrow(ProductExceptionSupplier.productNotFound(id));
        ProductRepository.save(productMapper.toProduct(product, updateProductRequest));
        return productMapper.toProductResponse(product);
    }
    public List<ProductResponse> findAll() {
        return ProductRepository.findAll().stream().map(productMapper::toProductResponse)
                .collect(java.util.stream.Collectors.toList());
    }
    public void delete(Long id) {
        Product product = ProductRepository.findById(id)
                .orElseThrow(ProductExceptionSupplier.productNotFound(id));
        ProductRepository.deleteById(product.getId());
    }
}

