package com.teknifity.products_simple_crud_operations.controller;

import java.util.Scanner;

import com.teknifity.products_simple_crud_operations.entity.Product;
import com.teknifity.products_simple_crud_operations.service.ProductService;

public class ProductController {

    private static final Scanner sc = new Scanner(System.in);
    private static final ProductService service = new ProductService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("      PRODUCT MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Product");
            System.out.println("2. Get Product By Id");
            System.out.println("3. Display All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Get Products By Brand");
            System.out.println("7. Get Products By Type");
            System.out.println("8. Get Products Between Prices");
            System.out.println("9. Sort Products By Price (ASC)");
            System.out.println("10. Sort Products By Price (DESC)");
            System.out.println("11. Count Products");
            System.out.println("12. Most Expensive Product");
            System.out.println("13. Cheapest Product");
            System.out.println("14. Exit");
            System.out.print("\nEnter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    getProductById();
                    break;

                case 3:
                    displayAllProducts();
                    break;

                case 4:
                    updateProduct();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    getProductsByBrand();
                    break;

                case 7:
                    getProductsByType();
                    break;

                case 8:
                    getProductsBetweenPrices();
                    break;

                case 9:
                    service.getProductsOrderByPriceAsc()
                           .forEach(System.out::println);
                    break;

                case 10:
                    service.getProductsOrderByPriceDesc()
                           .forEach(System.out::println);
                    break;

                case 11:
                    System.out.println(
                            "Total Products: "
                            + service.countProducts());
                    break;

                case 12:
                    System.out.println(
                            service.getMostExpensiveProduct());
                    break;

                case 13:
                    System.out.println(
                            service.getCheapestProduct());
                    break;

                case 14:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private static void addProduct() {

        Product product = new Product();

        System.out.print("Enter Product Brand: ");
        product.setProductBrand(sc.next());

        System.out.print("Enter Product Type: ");
        product.setProductType(sc.next());

        System.out.print("Enter Product Price: ");
        product.setProductPrice(sc.nextDouble());

        Product savedProduct = service.saveProduct(product);

        if (savedProduct != null) {
            System.out.println("Product Saved Successfully.");
        } else {
            System.out.println("Failed to Save Product.");
        }
    }

    private static void getProductById() {

        System.out.print("Enter Product Id: ");
        int id = sc.nextInt();

        Product product = service.getProductById(id);

        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product Not Found.");
        }
    }

    private static void displayAllProducts() {

        if (service.getAllProducts().isEmpty()) {
            System.out.println("No Products Available.");
            return;
        }

        service.getAllProducts()
               .forEach(System.out::println);
    }

    private static void updateProduct() {

        System.out.print("Enter Product Id: ");
        int id = sc.nextInt();

        Product updatedProduct = new Product();

        System.out.print("Enter New Brand: ");
        updatedProduct.setProductBrand(sc.next());

        System.out.print("Enter New Type: ");
        updatedProduct.setProductType(sc.next());

        System.out.print("Enter New Price: ");
        updatedProduct.setProductPrice(sc.nextDouble());

        boolean status =
                service.updateProductById(id, updatedProduct);

        System.out.println(
                status
                ? "Product Updated Successfully."
                : "Product Not Found.");
    }

    private static void deleteProduct() {

        System.out.print("Enter Product Id: ");
        int id = sc.nextInt();

        boolean status =
                service.deleteProductById(id);

        System.out.println(
                status
                ? "Product Deleted Successfully."
                : "Product Not Found.");
    }

    private static void getProductsByBrand() {

        System.out.print("Enter Brand: ");
        String brand = sc.next();

        service.getProductsByBrand(brand)
               .forEach(System.out::println);
    }

    private static void getProductsByType() {

        System.out.print("Enter Type: ");
        String type = sc.next();

        service.getProductsByType(type)
               .forEach(System.out::println);
    }

    private static void getProductsBetweenPrices() {

        System.out.print("Enter Minimum Price: ");
        double minPrice = sc.nextDouble();

        System.out.print("Enter Maximum Price: ");
        double maxPrice = sc.nextDouble();

        service.getProductsBetweenPrice(minPrice, maxPrice)
               .forEach(System.out::println);
    }
}