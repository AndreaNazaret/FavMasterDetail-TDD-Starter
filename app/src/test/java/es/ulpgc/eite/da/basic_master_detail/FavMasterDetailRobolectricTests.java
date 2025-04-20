package es.ulpgc.eite.da.basic_master_detail;

import static android.os.Looper.getMainLooper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.basic_master_detail.categories.CategoryListActivity;
import es.ulpgc.eite.da.basic_master_detail.product.ProductDetailActivity;
import es.ulpgc.eite.da.basic_master_detail.products.ProductListActivity;


// Project: Fav Master-Detail
// Created by Luis Hernandez 
// Copyright (c) 2025 EITE (ULPGC)
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class FavMasterDetailRobolectricTests {

    private CategoryListActivity categoryListActivity;

    @Before
    public void setUp() {
        categoryListActivity =
            Robolectric.buildActivity(CategoryListActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void testFavoritesFlow() {

        // Verificar nombres de categorías

        RecyclerView categoryList =
            categoryListActivity.findViewById(R.id.category_list_recycler);
        assertNotNull(categoryList);

        assertEquals(
            "Electronics",
            getTextFromRecyclerItem(categoryList, 0, R.id.category_name)
        );
        assertEquals(
            "Books",
            getTextFromRecyclerItem(categoryList, 1, R.id.category_name)
        );
        assertEquals(
            "Fashion",
            getTextFromRecyclerItem(categoryList, 2, R.id.category_name)
        );
        assertEquals(
            "Home Appliances",
            getTextFromRecyclerItem(categoryList, 3, R.id.category_name)
        );

        prepareRecyclerView(categoryList);
        shadowOf(getMainLooper()).idle();

        // Simular click en "Fashion"
        categoryList.findViewHolderForAdapterPosition(2).itemView.performClick();
        Intent productListIntent =
            shadowOf(categoryListActivity).getNextStartedActivity();
        assertEquals(
            ProductListActivity.class.getName(),
            productListIntent.getComponent().getClassName()
        );

        ProductListActivity productListActivity =
            Robolectric.buildActivity(ProductListActivity.class, productListIntent)
                .create()
                .start()
                .resume()
                .get();

        RecyclerView productList =
            productListActivity.findViewById(R.id.product_list_recycler);
        assertNotNull(productList);

        assertEquals(
            "T-shirt",
            getTextFromRecyclerItem(productList, 0, R.id.product_name)
        );
        assertEquals(
            "Jeans",
            getTextFromRecyclerItem(productList, 1, R.id.product_name)
        );
        assertEquals(
            "Sneakers",
            getTextFromRecyclerItem(productList, 2, R.id.product_name)
        );

        prepareRecyclerView(categoryList);
        shadowOf(getMainLooper()).idle();

        // Click en "Jeans"
        productList.findViewHolderForAdapterPosition(1).itemView.performClick();
        Intent detailIntent =
            shadowOf(productListActivity).getNextStartedActivity();
        assertEquals(
            ProductDetailActivity.class.getName(),
            detailIntent.getComponent().getClassName()
        );

        ProductDetailActivity detailActivity =
            Robolectric.buildActivity(ProductDetailActivity.class, detailIntent)
                .create()
                .start()
                .resume()
                .get();

        TextView productName = detailActivity.findViewById(R.id.product_name);
        assertEquals("Jeans", productName.getText().toString());

        ImageButton favButton = detailActivity.findViewById(R.id.favorite_button);
        assertTrue(hasDrawable(favButton, R.drawable.ic_black_heart));

        // Simular click en favorito
        favButton.performClick();
        assertTrue(hasDrawable(favButton, R.drawable.ic_red_heart));
    }

    // Helpers
    private String getTextFromRecyclerItem(RecyclerView recycler, int pos, int tvId) {
        prepareRecyclerView(recycler);
        shadowOf(getMainLooper()).idle();

        RecyclerView.ViewHolder viewHolder =
            recycler.findViewHolderForAdapterPosition(pos);
        if (viewHolder == null) return null;
        TextView textView = viewHolder.itemView.findViewById(tvId);
        return textView.getText().toString();
    }

    private boolean hasDrawable(ImageButton imageButton, int drawableId) {
        return shadowOf(imageButton.getDrawable()).getCreatedFromResId() == drawableId;
    }

    private void prepareRecyclerView(RecyclerView recyclerView) {
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
        );
        recyclerView.layout(0, 0, 1000, 1000);
    }

}
