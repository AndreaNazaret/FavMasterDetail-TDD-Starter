package es.ulpgc.eite.da.basic_master_detail.app;


import java.util.List;

import es.ulpgc.eite.da.basic_master_detail.categories.CategoryListState;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;
import es.ulpgc.eite.da.basic_master_detail.product.ProductDetailState;
import es.ulpgc.eite.da.basic_master_detail.products.ProductListState;

// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private CategoryListState savedCategoryListState;
    private ProductListState savedProductListState;
    private ProductDetailState savedProductDetailState;


    private ProductListToDetailState savedProductListToDetailState;
    private ProductToCategoryListState savedProductToCategoryListState;
    private ProductDetailToListState savedProductDetailToListState;
    private CategoryToProductListState savedCategoryToProductListState;


    private AppMediator() {
        //savedCategoryListState = new CategoryListState();
    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }

    // to reset the state when testing
    public static void resetInstance() {
        INSTANCE = null;
    }

    public CategoryListState getCategoryListScreenState() {
        return savedCategoryListState;
    }

    public void setCategoryListScreenState(CategoryListState state) {
        savedCategoryListState = state;
    }


    public ProductDetailState getProductDetailScreenState() {
        return savedProductDetailState;
    }

    public void setProductDetailScreenState(ProductDetailState state) {
        savedProductDetailState = state;
    }

    public ProductListState getProductListScreenState() {
        return savedProductListState;
    }

    public void setProductListScreenState(ProductListState state) {
        savedProductListState=state;
    }


    public void setCategoryToProductListScreenState(CategoryToProductListState state) {
        savedCategoryToProductListState = state;
    }

    public ProductToCategoryListState getProductToCategoryListScreenState() {
        ProductToCategoryListState state = savedProductToCategoryListState;
        savedProductToCategoryListState = null;
        return state;
    }

    public ProductDetailToListState getProductDetailToListScreenState() {
        ProductDetailToListState state = savedProductDetailToListState;
        savedProductDetailToListState = null;
        return state;
    }

    public void setProductListToDetailScreenState(ProductListToDetailState state) {
        savedProductListToDetailState = state;
    }

    public void setProductToCategoryListScreenState(ProductToCategoryListState state) {
        savedProductToCategoryListState = state;
    }

    public CategoryToProductListState getCategoryToProductListScreenState() {
        CategoryToProductListState state = savedCategoryToProductListState;
        savedCategoryToProductListState = null;
        return state;
    }

    public void setProductDetailToListScreenState(ProductDetailToListState state) {
        savedProductDetailToListState = state;
    }

    public ProductListToDetailState getProductListToDetailScreenState() {
        ProductListToDetailState state = savedProductListToDetailState;
        savedProductListToDetailState = null;
        return state;
    }

}