package es.ulpgc.eite.da.basic_master_detail.product;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;
import es.ulpgc.eite.da.basic_master_detail.app.CategoryToProductListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductDetailToListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductListToDetailState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductToCategoryListState;
import es.ulpgc.eite.da.basic_master_detail.categories.CategoryListState;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    public static String TAG = "Master-Detail.ProductDetailPresenter";

    private WeakReference<ProductDetailContract.View> view;
    private AppMediator mediator;
    private ProductDetailContract.Model model;
    private ProductDetailState state;

    public ProductDetailPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled()");

        // call the mediator initialize the state
        state = new ProductDetailState();

        // TODO: include code if necessary
        if (state.product == null) {
            ProductListToDetailState previusState = getStateFromPreviousScreen();
            ProductItem product = previusState.product;
            boolean productFavorite = previusState.isFavorite;
            model.onUpdatedDataFromPreviousScreen(product, productFavorite);

            state.product = product;
            state.isFavorite = productFavorite;
        }
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled()");

        // TODO: include code if necessary
        state = getSavedScreenState();
       // model.onUpdatedDataFromRecreatedScreen(state.product, state.isFavorite);
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");

        // TODO: include code if necessary

        // update the view
        if (state == null) {
            state = new ProductDetailState();
            ProductListToDetailState previusState = getStateFromPreviousScreen();
            ProductItem product = previusState.product;
            boolean productFavorite = previusState.isFavorite;
            model.onUpdatedDataFromPreviousScreen(product, productFavorite);

            state.product = product;
            state.isFavorite = productFavorite;
        }
        view.get().onRefreshViewWithUpdatedData(state);

    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");

        // TODO: include code if necessary

        ProductDetailToListState passState=new ProductDetailToListState();
        passState.isFavorite= state.isFavorite;
        passState.product=state.product;
        passStateToPreviousScreen(passState);


        view.get().navigateToPreviousScreen();
    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled()");

        // TODO: include code if necessary
        saveScreenState();
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled()");

    }

    @Override
    public void onFavoriteButtonClicked() {
        Log.e(TAG, "onFavoriteButtonClicked()");

        // TODO: include code if necessary
        state.isFavorite=!state.isFavorite;

        model.onUpdatedDataFromRecreatedScreen(state.product, state.isFavorite);

        saveScreenState();

        view.get().onRefreshViewWithUpdatedData(state);
    }

    private ProductDetailState getSavedScreenState() {
        return mediator.getProductDetailScreenState();
    }

    private void saveScreenState() {
        mediator.setProductDetailScreenState(state);
    }


    private void passStateToPreviousScreen(ProductDetailToListState state) {
        mediator.setProductDetailToListScreenState(state);
    }

    private ProductListToDetailState getStateFromPreviousScreen() {
        return mediator.getProductListToDetailScreenState();
    }

    @Override
    public void injectView(WeakReference<ProductDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductDetailContract.Model model) {
        this.model = model;
    }

}