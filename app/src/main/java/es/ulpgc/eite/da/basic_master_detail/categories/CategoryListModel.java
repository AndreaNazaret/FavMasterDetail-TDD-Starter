package es.ulpgc.eite.da.basic_master_detail.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ulpgc.eite.da.basic_master_detail.data.CatalogRepository;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class CategoryListModel implements CategoryListContract.Model {

    public static String TAG = "Master-Detail.CategoryListModel";

    private CatalogRepository catalog;
    private Map<CategoryItem, List<ProductItem>> favorites;


    public CategoryListModel() {
        catalog = CatalogRepository.getInstance();
        favorites = new HashMap<>();
    }

    @Override
    public List<CategoryItem> getCatalogData() {
        // Log.e(TAG, "getCatalogData()");

        return catalog.getCategories();
    }

    @Override
    public Map<CategoryItem, List<ProductItem>> getStoredData() {
        // Log.e(TAG, "getStoredData()");

        return favorites;
    }

    @Override
    public List<ProductItem> getStoredData(CategoryItem category) {
        // Log.e(TAG, "getStoredData()");

        if(category == null) {
            return new ArrayList<>();

        }else {
            if(! favorites.containsKey(category)) {
                favorites.put(category, new ArrayList<>());
            }

            return favorites.get(category);
        }
    }


    @Override
    public void onUpdatedDataFromRecreatedScreen(
        Map<CategoryItem, List<ProductItem>> favorites) {

        // Log.e(TAG, "onUpdatedDataFromRecreatedScreen()");

        this.favorites = favorites;
    }

    @Override
    public void onUpdatedDataFromNextScreen(
        CategoryItem category, List<ProductItem> favorites) {

        // Log.e(TAG, "onUpdatedDataFromNextScreen()");

        this.favorites.put(category, favorites);
    }

}