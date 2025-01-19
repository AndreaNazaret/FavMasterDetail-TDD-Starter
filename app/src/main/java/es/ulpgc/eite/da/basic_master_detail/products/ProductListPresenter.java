package es.ulpgc.eite.da.basic_master_detail.products;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;
import es.ulpgc.eite.da.basic_master_detail.app.CategoryToProductListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductDetailToListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductListToDetailState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductToCategoryListState;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductListPresenter implements ProductListContract.Presenter {

    public static String TAG = "Master-Detail.ProductListPresenter";

    private WeakReference<ProductListContract.View> view;
    private AppMediator mediator;
    private ProductListContract.Model model;
    private ProductListState state;

    public ProductListPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled()");

        // call the mediator initialize the state
        state = new ProductListState();


        // TODO: include code if necessary

    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled()");

        // TODO: include code if necessary
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");


        // TODO: include code if necessary

        // update the view
        view.get().onRefreshViewWithUpdatedData(state);

    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");

        // TODO: include code if necessary
    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled()");

        // TODO: include code if necessary
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled()");

    }

    @Override
    public void onItemSelected(ProductItem product) {
        Log.e(TAG, "onItemSelected()");

        // TODO: include code if necessary
    }

    private ProductListState getSavedScreenState() {
        return mediator.getProductListScreenState();
    }

    private void saveScreenState() {
        mediator.setProductListScreenState(state);
    }


    private ProductDetailToListState getStateFromNextScreen() {
        return mediator.getProductDetailToListScreenState();
    }

    private void passStateToNextScreen(ProductListToDetailState state) {
        mediator.setProductListToDetailScreenState(state);
    }

    private void passStateToPreviousScreen(ProductToCategoryListState state) {
        mediator.setProductToCategoryListScreenState(state);
    }

    private CategoryToProductListState getStateFromPreviousScreen() {
        return mediator.getCategoryToProductListScreenState();
    }

    @Override
    public void injectView(WeakReference<ProductListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductListContract.Model model) {
        this.model = model;
    }

}