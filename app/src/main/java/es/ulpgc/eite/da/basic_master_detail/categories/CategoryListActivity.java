package es.ulpgc.eite.da.basic_master_detail.categories;

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
import es.ulpgc.eite.da.basic_master_detail.products.ProductListActivity;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class CategoryListActivity extends AppCompatActivity
    implements CategoryListContract.View, View.OnClickListener {

    public static String TAG = "Master-Detail.CategoryListActivity";

    private CategoryListContract.Presenter presenter;

    private List<CategoryItem> categories;
    private RecyclerView categoryListRecycler;
    private CategoryListAdapter categoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        setTitle(R.string.title_category_list);

        // Log.e(TAG, "onCreate()");

        initView();

        // do the setup
        CategoryListScreen.configure(this);

        // init or update the state
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }

    private void initView() {

        categoryListRecycler = findViewById(R.id.category_list_recycler);
        categoryListRecycler.setLayoutManager(new LinearLayoutManager(this));

        categories = new ArrayList<>();
        categoryListAdapter = new CategoryListAdapter(this, categories);
        categoryListRecycler.setAdapter(categoryListAdapter);
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
    public void onRefreshViewWithUpdatedData(CategoryListViewModel viewModel) {
        //Log.e(TAG, "onRefreshViewWithUpdatedData()");

        // deal with the data
        categoryListAdapter.setItems(viewModel.categories);
        //categories.clear();
        //categoryListAdapter.addItems(viewModel.categories);

    }


    @Override
    public void onClick(View view) {

        CategoryItem category = (CategoryItem) view.getTag();
        presenter.onItemSelected(category);
    }

    @Override
    public void navigateToNextScreen() {
        // Log.e(TAG, "navigateToNextScreen()");

        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }


    @Override
    public void injectPresenter(CategoryListContract.Presenter presenter) {
        this.presenter = presenter;
    }

}