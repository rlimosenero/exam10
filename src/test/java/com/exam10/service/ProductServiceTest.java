package com.exam10.service;

import com.exam10.exception.ResourceNotFoundException;
import com.exam10.model.Product;
import com.exam10.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(1L, "Product Name", "Product Description", "food", 10, 20.0);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertEquals(product.getName(), createdProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", "Description1", "food", 10, 20.0);
        Product product2 = new Product(2L, "Product2", "Description2", "sports", 15, 25.0);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Product Name", "Product Description", "food", 10, 20.0);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);
        assertTrue(foundProduct.isPresent());
        assertEquals(product.getName(), foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Old Name", "Old Description", "food", 10, 20.0);
        Product updatedProduct = new Product(1L, "New Name", "New Description", "sports", 15, 25.0);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1L, updatedProduct);
        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Product Name", "Product Description", "food", 10, 20.0);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testUpdateProductNotFound() {
        Product updatedProduct = new Product(1L, "New Name", "New Description", "sports", 15, 25.0);
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            productService.updateProduct(1L, updatedProduct);
        });

        assertEquals("No Product found with this id :: 1", thrown.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(0)).save(any(Product.class));
    }

    @Test
    public void testDeleteProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProduct(1L);
        });

        assertEquals("No Product found with this id : 1", thrown.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(0)).delete(any(Product.class));
    }
}

