package com.pragma.powerup.domain.model;

import java.util.List;

public class Franchise {
    private Long id;
    private String name;
    private Long branchId;
    private List<Branch> branchList;

    public Franchise(Long id, String name, List<Branch> branchList) {
        this.id = id;
        this.name = name;
        this.branchList = branchList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
