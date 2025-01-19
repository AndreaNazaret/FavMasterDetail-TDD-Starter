package es.ulpgc.eite.da.basic_master_detail.product;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductDetailScreen {

    public static void configure(ProductDetailContract.View view) {

        WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(mediator);

        ProductDetailContract.Model model = new ProductDetailModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}