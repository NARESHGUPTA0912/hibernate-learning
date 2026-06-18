package com.teknifity.products_simple_crud_operations.dao;

import java.util.List;

import com.teknifity.products_simple_crud_operations.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProductDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernate");

    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction et = em.getTransaction();

    // ================= INSERT =================

    public Product insertProductDetails(Product product) {

        if (product == null) {
            return null;
        }

        try {
            et.begin();
            em.persist(product);
            et.commit();
            return product;

        } catch (Exception e) {

            if (et.isActive()) {
                et.rollback();
            }

            e.printStackTrace();
            return null;
        }
    }

    // ================= READ =================

    public Product getProductByIdDao(int productId) {
        return em.find(Product.class, productId);
    }

    public List<Product> getAllProducts() {
        return em.createQuery(
                "SELECT p FROM Product p",
                Product.class)
                .getResultList();
    }

    // ================= UPDATE =================

    public boolean updateProductByIdDao(int productId, Product updatedProduct) {

        Product existingProduct = em.find(Product.class, productId);

        if (existingProduct == null) {
            return false;
        }

        try {

            et.begin();

            existingProduct.setProductBrand(updatedProduct.getProductBrand());
            existingProduct.setProductType(updatedProduct.getProductType());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());

            em.merge(existingProduct);

            et.commit();

            return true;

        } catch (Exception e) {

            if (et.isActive()) {
                et.rollback();
            }

            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE =================

    public boolean deleteProductByIdDao(int productId) {

        Product product = em.find(Product.class, productId);

        if (product == null) {
            return false;
        }

        try {

            et.begin();
            em.remove(product);
            et.commit();

            return true;

        } catch (Exception e) {

            if (et.isActive()) {
                et.rollback();
            }

            e.printStackTrace();
            return false;
        }
    }

    public int deleteAllProducts() {

        try {

            et.begin();

            int deletedRows = em.createQuery(
                    "DELETE FROM Product")
                    .executeUpdate();

            et.commit();

            return deletedRows;

        } catch (Exception e) {

            if (et.isActive()) {
                et.rollback();
            }

            e.printStackTrace();
            return 0;
        }
    }

    // ================= FILTERING =================

    public List<Product> getProductsByBrand(String brand) {

        return em.createQuery(
                "SELECT p FROM Product p WHERE p.productBrand = :brand",
                Product.class)
                .setParameter("brand", brand)
                .getResultList();
    }

    public List<Product> getProductsByType(String type) {

        return em.createQuery(
                "SELECT p FROM Product p WHERE p.productType = :type",
                Product.class)
                .setParameter("type", type)
                .getResultList();
    }

    public List<Product> getProductsByPrice(double price) {

        return em.createQuery(
                "SELECT p FROM Product p WHERE p.productPrice = :price",
                Product.class)
                .setParameter("price", price)
                .getResultList();
    }

    public List<Product> getProductsByBrandAndType(
            String brand,
            String type) {

        return em.createQuery(
                "SELECT p FROM Product p " +
                "WHERE p.productBrand = :brand " +
                "AND p.productType = :type",
                Product.class)
                .setParameter("brand", brand)
                .setParameter("type", type)
                .getResultList();
    }

    // ================= PRICE FILTERS =================

    public List<Product> getProductsBelowPrice(double price) {

        return em.createQuery(
                "SELECT p FROM Product p WHERE p.productPrice < :price",
                Product.class)
                .setParameter("price", price)
                .getResultList();
    }

    public List<Product> getProductsAbovePrice(double price) {

        return em.createQuery(
                "SELECT p FROM Product p WHERE p.productPrice > :price",
                Product.class)
                .setParameter("price", price)
                .getResultList();
    }

    public List<Product> getProductsBetweenPrice(
            double minPrice,
            double maxPrice) {

        return em.createQuery(
                "SELECT p FROM Product p " +
                "WHERE p.productPrice BETWEEN :minPrice AND :maxPrice",
                Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    // ================= SORTING =================

    public List<Product> getProductsOrderByPriceAsc() {

        return em.createQuery(
                "SELECT p FROM Product p ORDER BY p.productPrice ASC",
                Product.class)
                .getResultList();
    }

    public List<Product> getProductsOrderByPriceDesc() {

        return em.createQuery(
                "SELECT p FROM Product p ORDER BY p.productPrice DESC",
                Product.class)
                .getResultList();
    }

    public List<Product> getProductsOrderByBrand() {

        return em.createQuery(
                "SELECT p FROM Product p ORDER BY p.productBrand",
                Product.class)
                .getResultList();
    }

    // ================= SEARCH =================

    public List<Product> searchProductsByBrand(String keyword) {

        return em.createQuery(
                "SELECT p FROM Product p " +
                "WHERE p.productBrand LIKE :keyword",
                Product.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    // ================= AGGREGATE FUNCTIONS =================

    public long countProducts() {

        return em.createQuery(
                "SELECT COUNT(p) FROM Product p",
                Long.class)
                .getSingleResult();
    }

    public long countProductsByBrand(String brand) {

        return em.createQuery(
                "SELECT COUNT(p) FROM Product p " +
                "WHERE p.productBrand = :brand",
                Long.class)
                .setParameter("brand", brand)
                .getSingleResult();
    }

    public Double getMaxPrice() {

        return em.createQuery(
                "SELECT MAX(p.productPrice) FROM Product p",
                Double.class)
                .getSingleResult();
    }

    public Double getMinPrice() {

        return em.createQuery(
                "SELECT MIN(p.productPrice) FROM Product p",
                Double.class)
                .getSingleResult();
    }

    public Double getAveragePrice() {

        return em.createQuery(
                "SELECT AVG(p.productPrice) FROM Product p",
                Double.class)
                .getSingleResult();
    }

    // ================= SPECIAL QUERIES =================

    public Product getMostExpensiveProduct() {

        List<Product> products = em.createQuery(
                "SELECT p FROM Product p " +
                "ORDER BY p.productPrice DESC",
                Product.class)
                .setMaxResults(1)
                .getResultList();

        return products.isEmpty() ? null : products.get(0);
    }

    public Product getCheapestProduct() {

        List<Product> products = em.createQuery(
                "SELECT p FROM Product p " +
                "ORDER BY p.productPrice ASC",
                Product.class)
                .setMaxResults(1)
                .getResultList();

        return products.isEmpty() ? null : products.get(0);
    }

    // ================= EXISTENCE CHECKS =================

    public boolean existsById(int productId) {
        return em.find(Product.class, productId) != null;
    }

    public boolean existsByBrand(String brand) {

        Long count = em.createQuery(
                "SELECT COUNT(p) FROM Product p " +
                "WHERE p.productBrand = :brand",
                Long.class)
                .setParameter("brand", brand)
                .getSingleResult();

        return count > 0;
    }

    // ================= DISTINCT VALUES =================

    public List<String> getAllBrands() {

        return em.createQuery(
                "SELECT DISTINCT p.productBrand FROM Product p",
                String.class)
                .getResultList();
    }

    public List<String> getAllTypes() {

        return em.createQuery(
                "SELECT DISTINCT p.productType FROM Product p",
                String.class)
                .getResultList();
    }

    // ================= PAGINATION =================

    public List<Product> getProducts(int pageNumber, int pageSize) {

        return em.createQuery(
                "SELECT p FROM Product p",
                Product.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    // ================= RESOURCE MANAGEMENT =================

    public void closeEntityManager() {

        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}