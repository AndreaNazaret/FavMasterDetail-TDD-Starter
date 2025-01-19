package es.ulpgc.eite.da.basic_master_detail.products;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basic_master_detail.app.AppMediator;
import es.ulpgc.eite.da.basic_master_detail.data.CatalogRepository;


// Project: Basic Master-Detail
// Created by Luis Hernandez on 13/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class ProductListScreen {

    public static void configure(ProductListContract.View view) {

        WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        ProductListContract.Presenter presenter = new ProductListPresenter(mediator);

        ProductListContract.Model model = new ProductListModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}