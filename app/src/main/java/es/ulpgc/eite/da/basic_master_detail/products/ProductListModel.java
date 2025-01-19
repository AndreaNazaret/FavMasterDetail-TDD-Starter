package es.ulpgc.eite.da.basic_master_detail.products;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.basic_master_detail.data.CatalogRepository;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductListModel implements ProductListContract.Model {

    public static String TAG = "Master-Detail.ProductListModel";

    private List<ProductItem> favorites;
    private CategoryItem category;

    public ProductListModel() {
        favorites= new ArrayList<>();
    }


    @Override
    public CategoryItem getCatalogData() {
        // Log.e(TAG, "getCatalogData()");

        return category;
    }


    @Override
    public List<ProductItem> getStoredData() {
        // Log.e(TAG, "getStoredData()");

        return favorites;
    }


    @Override
    public boolean isFavorite(ProductItem product) {
        return favorites.contains(product);
    }


    @Override
    public void onUpdatedDataFromRecreatedScreen(
        CategoryItem category, List<ProductItem> favorites) {

        // Log.e(TAG, "onUpdatedDataFromRecreatedScreen()");

        this.category=category;
        this.favorites=favorites;
    }

    @Override
    public void onUpdatedDataFromNextScreen(
        ProductItem product, boolean isFavorite) {

        // Log.e(TAG, "onUpdatedDataFromNextScreen()");

        if(isFavorite) {
            if(!favorites.contains(product)) {
                favorites.add(product);
            }

        }else{
            if(favorites.contains(product)) {
                favorites.remove(product);
            }
        }

    }

    @Override
    public void onUpdatedDataFromPreviousScreen(
        CategoryItem category, List<ProductItem> favorites) {

        // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");

        this.category=category;
        this.favorites = favorites;
    }

}