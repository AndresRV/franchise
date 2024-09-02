package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchesWithProductsMaxStockResponse {
    private String branchName;
    private String productName;
    private Integer productStock;
}
