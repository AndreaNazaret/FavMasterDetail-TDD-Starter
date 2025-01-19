package es.ulpgc.eite.da.basic_master_detail.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.basic_master_detail.R;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;
import es.ulpgc.eite.da.basic_master_detail.product.ProductDetailActivity;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductListActivity extends AppCompatActivity
    implements ProductListContract.View, View.OnClickListener {

    public static String TAG = "Master-Detail.ProductListActivity";

    private ProductListContract.Presenter presenter;

    private RecyclerView productListRecycler;
    private ProductListAdapter productListAdapter;
    private List<ProductItem> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle(R.string.title_product_list);

        // Log.e(TAG, "onCreate()");

        initView();

        // do the setup
        ProductListScreen.configure(this);

        // init or update the state
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }

    private void initView() {

        productListRecycler = findViewById(R.id.product_list_recycler);
        productListRecycler.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<>();
        productListAdapter = new ProductListAdapter(this, products);
        productListRecycler.setAdapter(productListAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Log.e(TAG, "onResume()");

        // load the data
        presenter.onResumeCalled();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Log.e(TAG, "onBackPressed()");

        presenter.onBackButtonPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Log.e(TAG, "onPause()");

        presenter.onPauseCalled();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Log.e(TAG, "onDestroy()");

        presenter.onDestroyCalled();
    }


    @Override
    public void onRefreshViewWithUpdatedData(ProductListViewModel viewModel) {
        //Log.e(TAG, "onRefreshViewWithUpdatedData()");

        // deal with the data
        CategoryItem category = viewModel.category;
        productListAdapter.setItems(category.getProducts());
        //products.clear();
        //productListAdapter.addItems(viewModel.products);

    }


    @Override
    public void onClick(View view) {

        ProductItem product = (ProductItem) view.getTag();
        presenter.onItemSelected(product);

    }

    @Override
    public void navigateToNextScreen() {
        // Log.e(TAG, "navigateToNextScreen()");

        Intent intent = new Intent(this, ProductDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToPreviousScreen() {
        // Log.e(TAG, "navigateToPreviousScreen()");

        finish();
    }

    @Override
    public void injectPresenter(ProductListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}