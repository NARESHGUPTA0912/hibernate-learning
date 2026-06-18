package com.teknifity.products_simple_crud_operations.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productBrand;
    private String productType;
    private double productPrice;
    
    @Override
    public String toString() {
        return "Product [productId=" + productId
                + ", productBrand=" + productBrand
                + ", productType=" + productType
                + ", productPrice=" + productPrice + "]";
    }
}