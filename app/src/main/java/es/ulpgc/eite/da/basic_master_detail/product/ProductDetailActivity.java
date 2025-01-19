package es.ulpgc.eite.da.basic_master_detail.product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.basic_master_detail.R;
import es.ulpgc.eite.da.basic_master_detail.data.CatalogRepository;
import es.ulpgc.eite.da.basic_master_detail.data.ProductItem;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductDetailActivity
    extends AppCompatActivity implements ProductDetailContract.View {

    public static String TAG = "Master-Detail.ProductDetailActivity";

    private ProductDetailContract.Presenter presenter;

    private TextView productNameTextView, productDescrTextView;
    private ImageButton favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setTitle(R.string.title_product_detail);

        // Log.e(TAG, "onCreate()");

        productNameTextView = findViewById(R.id.product_name);
        productDescrTextView = findViewById(R.id.product_description);
        favoriteButton = findViewById(R.id.favorite_button);

        favoriteButton.setOnClickListener(
            v -> presenter.onFavoriteButtonClicked());

        // do the setup
        ProductDetailScreen.configure(this);

        // init or update the state
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
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
    public void onRefreshViewWithUpdatedData(ProductDetailViewModel viewModel) {
        //Log.e(TAG, "onRefreshViewWithUpdatedData()");

        // deal with the data
        ProductItem product = viewModel.product;
        productNameTextView.setText(product.getName());
        productDescrTextView.setText(product.getDescription());

        if (viewModel.isFavorite) {
            favoriteButton.setImageResource(R.drawable.ic_red_heart);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_black_heart);
        }
    }

    @Override
    public void navigateToPreviousScreen() {
        // Log.e(TAG, "navigateToPreviousScreen()");

        finish();
    }

    @Override
    public void injectPresenter(ProductDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}