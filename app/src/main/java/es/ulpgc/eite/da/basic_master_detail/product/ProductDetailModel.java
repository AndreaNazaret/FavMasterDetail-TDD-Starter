package es.ulpgc.eite.da.basic_master_detail.product;

import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;

// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductDetailModel implements ProductDetailContract.Model {

    public static String TAG = "Master-Detail.ProductDetailModel";

    private ProductItem product;
    private boolean isFavorite;

    public ProductDetailModel() {

    }

    @Override
    public ProductItem getCatalogData() {
        // Log.e(TAG, "getCatalogData()");

        return product;
    }

    @Override
    public boolean getStoredData() {
        // Log.e(TAG, "getStoredData()");

        return isFavorite;
    }


    @Override
    public void updateStoredData() {
        isFavorite = !isFavorite;
    }


    @Override
    public void onUpdatedDataFromRecreatedScreen(
        ProductItem product, boolean isFavorite) {

        // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");

        this.product = product;
        this.isFavorite=isFavorite;
    }


    @Override
    public void onUpdatedDataFromPreviousScreen(
        ProductItem product, boolean isFavorite) {

        // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");

        this.product = product;
        this.isFavorite=isFavorite;
    }


}