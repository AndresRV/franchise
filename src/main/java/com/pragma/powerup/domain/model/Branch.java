package com.pragma.powerup.domain.model;

import java.util.List;

public class Branch {
    private Long id;
    private Long franchiseId;
    private String name;
    private List<Product> productList;

    public Branch(Long id, Long franchiseId, String name, List<Product> productList) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.name = name;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
