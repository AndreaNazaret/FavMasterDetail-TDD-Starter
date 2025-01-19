package es.ulpgc.eite.da.basic_master_detail.product;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public interface ProductDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(ProductDetailViewModel viewModel);

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

        void onFavoriteButtonClicked();

    }

    interface Model {
        ProductItem getCatalogData();

        boolean getStoredData();

        void updateStoredData();

        void onUpdatedDataFromRecreatedScreen(
            ProductItem product, boolean isFavorite);

        void onUpdatedDataFromPreviousScreen(
            ProductItem product, boolean isFavorite);

    }

}