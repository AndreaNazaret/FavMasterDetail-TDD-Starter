package es.ulpgc.eite.da.basic_master_detail.categories;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public interface CategoryListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(CategoryListViewModel viewModel);

        void navigateToNextScreen();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResumeCalled();

        void onCreateCalled();

        void onRecreateCalled();

        void onBackButtonPressed();

        void onPauseCalled();

        void onDestroyCalled();

        void onItemSelected(CategoryItem category);
    }

    interface Model {
        List<CategoryItem> getCatalogData();

        Map<CategoryItem, List<ProductItem>> getStoredData();
        List<ProductItem> getStoredData(CategoryItem category);

        void onUpdatedDataFromRecreatedScreen(
            Map<CategoryItem, List<ProductItem>> favorites);

        void onUpdatedDataFromNextScreen(
            CategoryItem category, List<ProductItem> favorites);

    }

}