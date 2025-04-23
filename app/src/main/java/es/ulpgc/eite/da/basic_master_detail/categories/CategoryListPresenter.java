package es.ulpgc.eite.da.basic_master_detail.categories;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;
import es.ulpgc.eite.da.basic_master_detail.app.CategoryToProductListState;
import es.ulpgc.eite.da.basic_master_detail.app.ProductToCategoryListState;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


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
        state = getSavedScreenState();
        if (state.favorites != null) {
            for (Map.Entry<CategoryItem, List<ProductItem>> entry : state.favorites.entrySet()) {
                model.onUpdatedDataFromNextScreen(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");

        // TODO: include code if necessary
        ProductToCategoryListState nextState = getStateFromNextScreen();
        if (nextState != null && nextState.favorites != null && nextState.category != null) {
            model.onUpdatedDataFromNextScreen(nextState.category, nextState.favorites);

            if (state.favorites == null) {
                state.favorites = new HashMap<>();
            }
            state.favorites.put(nextState.category, nextState.favorites);
        } else {
            state.favorites = model.getStoredData();
        }


        if (state == null) {
            state = new CategoryListState();
            state.categories = model.getCatalogData();
            state.favorites = model.getStoredData();
        }
        view.get().onRefreshViewWithUpdatedData(state);

    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");

        // TODO: include code if necessary
        // Navegación por defecto, no se requiere lógica personalizada aquí.
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
    public void onItemSelected(CategoryItem category) {
        Log.e(TAG, "onItemSelected()");

        // TODO: include code if necessary
        CategoryToProductListState newState = new CategoryToProductListState();
        newState.category = category;
        newState.favorites= model.getStoredData(category);
        passStateToNextScreen(newState);

        view.get().navigateToNextScreen();
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