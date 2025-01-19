package es.ulpgc.eite.da.basic_master_detail.categories;


import java.util.List;
import java.util.Map;

import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;

// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class CategoryListState extends CategoryListViewModel {

    // put the state data here
    public Map<CategoryItem, List<ProductItem>> favorites;

}