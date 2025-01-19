package es.ulpgc.eite.da.basic_master_detail.products;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public interface ProductListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(ProductListViewModel viewModel);

        void navigateToNextScreen();

        void navigateToPreviousScreen();
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

        void onItemSelected(ProductItem product);
    }

    interface Model {

        CategoryItem getCatalogData();
        List<ProductItem> getStoredData();

        void onUpdatedDataFromRecreatedScreen(
            CategoryItem category, List<ProductItem> favorites);

        void onUpdatedDataFromNextScreen(
            ProductItem product, boolean isFavorite);

        void onUpdatedDataFromPreviousScreen(
            CategoryItem category, List<ProductItem> favorites);

        boolean isFavorite(ProductItem product);

    }

}