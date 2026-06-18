package com.teknifity.products_simple_crud_operations.service;

import java.util.List;

import com.teknifity.products_simple_crud_operations.dao.ProductDao;
import com.teknifity.products_simple_crud_operations.entity.Product;

public class ProductService {

    private final ProductDao dao = new ProductDao();

    // ================= SAVE =================

    public Product saveProduct(Product product) {

        validateProduct(product);

        return dao.insertProductDetails(product);
    }

    // ================= GET =================

    public Product getProductById(int productId) {

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Product Id must be greater than 0");
        }

        return dao.getProductByIdDao(productId);
    }

    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    // ================= UPDATE =================

    public boolean updateProductById(int productId,
                                     Product updatedProduct) {

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Product Id");
        }

        validateProduct(updatedProduct);

        return dao.updateProductByIdDao(productId, updatedProduct);
    }

    // ================= DELETE =================

    public boolean deleteProductById(int productId) {

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Product Id");
        }

        return dao.deleteProductByIdDao(productId);
    }

    public int deleteAllProducts() {

        if (dao.countProducts() == 0) {
            throw new RuntimeException(
                    "No products available to delete");
        }

        return dao.deleteAllProducts();
    }

    // ================= FILTERING =================

    public List<Product> getProductsByBrand(String brand) {

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException(
                    "Brand cannot be empty");
        }

        return dao.getProductsByBrand(brand);
    }

    public List<Product> getProductsByType(String type) {

        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException(
                    "Type cannot be empty");
        }

        return dao.getProductsByType(type);
    }

    public List<Product> getProductsByPrice(double price) {

        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than 0");
        }

        return dao.getProductsByPrice(price);
    }

    public List<Product> getProductsByBrandAndType(
            String brand,
            String type) {

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException(
                    "Brand cannot be empty");
        }

        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException(
                    "Type cannot be empty");
        }

        return dao.getProductsByBrandAndType(brand, type);
    }

    // ================= PRICE FILTERS =================

    public List<Product> getProductsBelowPrice(double price) {

        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than 0");
        }

        return dao.getProductsBelowPrice(price);
    }

    public List<Product> getProductsAbovePrice(double price) {

        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than 0");
        }

        return dao.getProductsAbovePrice(price);
    }

    public List<Product> getProductsBetweenPrice(
            double minPrice,
            double maxPrice) {

        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative");
        }

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException(
                    "Minimum price cannot be greater than maximum price");
        }

        return dao.getProductsBetweenPrice(minPrice, maxPrice);
    }

    // ================= SORTING =================

    public List<Product> getProductsOrderByPriceAsc() {
        return dao.getProductsOrderByPriceAsc();
    }

    public List<Product> getProductsOrderByPriceDesc() {
        return dao.getProductsOrderByPriceDesc();
    }

    public List<Product> getProductsOrderByBrand() {
        return dao.getProductsOrderByBrand();
    }

    // ================= SEARCH =================

    public List<Product> searchProductsByBrand(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                    "Search keyword cannot be empty");
        }

        return dao.searchProductsByBrand(keyword);
    }

    // ================= AGGREGATE =================

    public long countProducts() {
        return dao.countProducts();
    }

    public long countProductsByBrand(String brand) {

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException(
                    "Brand cannot be empty");
        }

        return dao.countProductsByBrand(brand);
    }

    public Double getMaxPrice() {
        return dao.getMaxPrice();
    }

    public Double getMinPrice() {
        return dao.getMinPrice();
    }

    public Double getAveragePrice() {
        return dao.getAveragePrice();
    }

    // ================= SPECIAL =================

    public Product getMostExpensiveProduct() {
        return dao.getMostExpensiveProduct();
    }

    public Product getCheapestProduct() {
        return dao.getCheapestProduct();
    }

    // ================= EXISTS =================

    public boolean existsById(int productId) {

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Product Id");
        }

        return dao.existsById(productId);
    }

    public boolean existsByBrand(String brand) {

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException(
                    "Brand cannot be empty");
        }

        return dao.existsByBrand(brand);
    }

    // ================= DISTINCT =================

    public List<String> getAllBrands() {
        return dao.getAllBrands();
    }

    public List<String> getAllTypes() {
        return dao.getAllTypes();
    }

    // ================= PAGINATION =================

    public List<Product> getProducts(
            int pageNumber,
            int pageSize) {

        if (pageNumber <= 0) {
            throw new IllegalArgumentException(
                    "Page number must be greater than 0");
        }

        if (pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Page size must be greater than 0");
        }

        return dao.getProducts(pageNumber, pageSize);
    }

    // ================= PRIVATE VALIDATION =================

    private void validateProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException(
                    "Product cannot be null");
        }

        if (product.getProductBrand() == null ||
            product.getProductBrand().isBlank()) {

            throw new IllegalArgumentException(
                    "Product brand cannot be empty");
        }

        if (product.getProductType() == null ||
            product.getProductType().isBlank()) {

            throw new IllegalArgumentException(
                    "Product type cannot be empty");
        }

        if (product.getProductPrice() <= 0) {

            throw new IllegalArgumentException(
                    "Product price must be greater than 0");
        }
    }
}