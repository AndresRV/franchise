package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.infrastructure.input.BranchFunctionHandler;
import com.pragma.powerup.infrastructure.input.FranchiseFunctionHandler;
import com.pragma.powerup.infrastructure.input.ProductFunctionHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {
    private static final String PATH_PRODUCT = "productFunction";
    private static final String PATH_PRODUCT_BY_ID = "productFunction/{id}";
    private static final String BRANCH_BY_ID = "/branch/{idBranch}";
    private static final String STOCK = "/stock";
    private static final String NAME = "/name";

    private static final String PATH_BRANCH = "branchFunction";
    private static final String PATH_BRANCH_BY_ID = "branchFunction/{id}";
    private static final String FRANCHISE_BY_ID = "/franchise/{idFranchise}";
    private static final String PRODUCT = "/product";

    private static final String PATH_FRANCHISE = "franchiseFunction";
    private static final String PATH_FRANCHISE_BY_ID = "franchiseFunction/{id}";

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    RouterFunction<ServerResponse> routerProduct(ProductFunctionHandler productHandler) {
        return RouterFunctions.route()
                .GET(PATH_PRODUCT, req -> productHandler.getAllProducts())
                .POST(PATH_PRODUCT + BRANCH_BY_ID, productHandler::addProductToBranch)
                .DELETE(PATH_PRODUCT_BY_ID + BRANCH_BY_ID, productHandler::removeProductToBranch)
                .PATCH(PATH_PRODUCT_BY_ID + STOCK, productHandler::updateProductStock)
                .PATCH(PATH_PRODUCT_BY_ID + NAME, productHandler::updateProductName)
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> routerBranch(BranchFunctionHandler branchHandler) {
        return RouterFunctions.route()
                .GET(PATH_BRANCH, req -> branchHandler.getAllBranches())
                .POST(PATH_BRANCH + FRANCHISE_BY_ID, branchHandler::addBranchToFranchise)
                .GET(PATH_BRANCH + FRANCHISE_BY_ID + PRODUCT, branchHandler::getProductsWithMaxStockByFranchise)
                .PATCH(PATH_BRANCH_BY_ID + NAME, branchHandler::updateBranchName)
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> routerFranchise(FranchiseFunctionHandler franchiseHandler) {
        return RouterFunctions.route()
                .GET(PATH_FRANCHISE, req -> franchiseHandler.getAllBranches())
                .POST(PATH_FRANCHISE, franchiseHandler::addFranchise)
                .PATCH(PATH_FRANCHISE_BY_ID + NAME, franchiseHandler::updateFranchiseName)
                .build();
    }
}
