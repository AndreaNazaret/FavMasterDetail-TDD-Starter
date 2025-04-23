package es.ulpgc.eite.da.basic_master_detail.products;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;
import es.ulpgc.eite.da.basic_master_detail.app.CategoryToProductListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductDetailToListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductListToDetailState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductToCategoryListState;
import es.ulpgc.eite.da.basic_master_detail.categories.CategoryListState;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
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

        ProductDetailToListState FavoriteState = getStateFromNextScreen();
        if (FavoriteState != null) {
            model.onUpdatedDataFromNextScreen(FavoriteState.product, FavoriteState.isFavorite);
        }

        if (state == null) {
            state = getSavedScreenState();
        }

        // TODO: include code if necessary
        if (state.category == null) {
            CategoryToProductListState previousState = getStateFromPreviousScreen();
            CategoryItem category = previousState.category;

            if (previousState.favorites != null) {
                model.onUpdatedDataFromPreviousScreen(category, previousState.favorites);
                state.favorites = previousState.favorites;
            } else {
                model.onUpdatedDataFromPreviousScreen(category, model.getStoredData());
                state.favorites = model.getStoredData();
            }

            state.category = model.getCatalogData();
        }

        state.favorites = model.getStoredData();
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled()");

        // TODO: include code if necessary
        state = getSavedScreenState();
        model.onUpdatedDataFromRecreatedScreen(state.category, state.favorites);
        state.favorites = model.getStoredData();

    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");

        // TODO: include code if necessary
        if (state == null) {
            state = getSavedScreenState();
        }

        if (state == null || state.category == null) {
            state = new ProductListState();
            CategoryToProductListState previousState = getStateFromPreviousScreen();
            CategoryItem category = previousState.category;
            model.onUpdatedDataFromPreviousScreen(category, new ArrayList<>());
            state.category=model.getCatalogData();
        }

       ProductDetailToListState FavoriteState = getStateFromNextScreen();
        if(FavoriteState!=null){
                model.onUpdatedDataFromNextScreen(FavoriteState.product, FavoriteState.isFavorite);
        }

        state.favorites= model.getStoredData();


        // update the view
        view.get().onRefreshViewWithUpdatedData(state);



    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");

        // TODO: include code if necessary
        ProductToCategoryListState passState = new ProductToCategoryListState();
        passState.category=state.category;
        passState.favorites=state.favorites;
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
    public void onItemSelected(ProductItem product) {
        Log.e(TAG, "onItemSelected()");

        // TODO: include code if necessary
        ProductListToDetailState nextState = new ProductListToDetailState();
        nextState.product=product;
        nextState.isFavorite= model.isFavorite(product);

        passStateToNextScreen(nextState);
        view.get().navigateToNextScreen();
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