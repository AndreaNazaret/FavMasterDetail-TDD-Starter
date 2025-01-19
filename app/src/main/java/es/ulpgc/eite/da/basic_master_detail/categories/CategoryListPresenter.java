package es.ulpgc.eite.da.basic_master_detail.categories;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;
import es.ulpgc.eite.da.basic_master_detail.app.CategoryToProductListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductToCategoryListState;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class CategoryListPresenter implements CategoryListContract.Presenter {

    public static String TAG = "Master-Detail.CategoryListPresenter";

    private WeakReference<CategoryListContract.View> view;
    private AppMediator mediator;
    private CategoryListContract.Model model;
    private CategoryListState state;

    public CategoryListPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled()");

        // call the mediator initialize the state
        state = new CategoryListState();
        state.categories =  model.getCatalogData();
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
    public void onItemSelected(CategoryItem category) {
        Log.e(TAG, "onItemSelected()");

        // TODO: include code if necessary
    }

    private CategoryListState getSavedScreenState() {
        return mediator.getCategoryListScreenState();
    }

    private void saveScreenState() {
        mediator.setCategoryListScreenState(state);
    }

    private ProductToCategoryListState getStateFromNextScreen() {
        return mediator.getProductToCategoryListScreenState();
    }

    private void passStateToNextScreen(CategoryToProductListState state) {
        mediator.setCategoryToProductListScreenState(state);
    }


    @Override
    public void injectView(WeakReference<CategoryListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CategoryListContract.Model model) {
        this.model = model;
    }

}