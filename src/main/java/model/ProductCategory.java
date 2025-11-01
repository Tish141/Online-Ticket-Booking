/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.List;

/**
 *
 * @author Tran Hieu Nghia - CE191115
 */
public class ProductCategory {
    private int id;
    private String name;
    private String description;
    private List<Product> products;

    public ProductCategory() {
    }
    
    public ProductCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
}
