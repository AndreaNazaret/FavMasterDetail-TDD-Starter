package es.ulpgc.eite.da.basic_master_detail;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MasterDetailRobolectricTests {

    RobolectricTestSteps testSteps = new RobolectricTestSteps();

    @Test
    public void appTest() {

        /*
        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(0, R.id.category_name))
            .check(matches(withText("Electronics")));

        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(1, R.id.category_name))
            .check(matches(withText("Books")));
        */

        testSteps.iniciarPantallaDeListaDeCategorias();
        testSteps.mostrarListaDeCategorias(4);
        testSteps.mostrarCategoriaEnLista(0, "Electronics");
        testSteps.mostrarCategoriaEnLista(1, "Books");

        /*
        ViewInteraction recyclerView1 =
            onView(new RecyclerViewMatcher(R.id.category_recycler)
                .atPositionOnView(2, R.id.category_name))
                .check(matches(withText("Fashion")));
        recyclerView1.perform(click());
        */

        testSteps.mostrarCategoriaEnLista(2, "Fashion");
        testSteps.pulsarCategoriaEnLista(2);

        /*
        onView(new RecyclerViewMatcher(R.id.product_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("T-shirt")));

        onView(new RecyclerViewMatcher(R.id.product_recycler)
            .atPositionOnView(1, R.id.product_name))
            .check(matches(withText("Jeans")));
        */

        //testSteps.iniciarPantallaDeListaDeProductos(2);
        testSteps.mostrarListaDeProductos(3);
        testSteps.mostrarProductoEnLista(0, "T-shirt");
        testSteps.mostrarProductoEnLista(1, "Jeans");

        /*
        pressBack();


        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(0, R.id.category_name))
            .check(matches(withText("Electronics")));
        */

        testSteps.pulsarBackEnListaDeProductos();
        testSteps.mostrarCategoriaEnLista(0, "Electronics");
        testSteps.mostrarCategoriaEnLista(2, "Fashion");

        /*
        ViewInteraction recyclerView2 =
            onView(new RecyclerViewMatcher(R.id.category_recycler)
                .atPositionOnView(0, R.id.category_name))
                .check(matches(withText("Electronics")));
        recyclerView2.perform(click());


        onView(new RecyclerViewMatcher(R.id.product_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("Smartphone")));
        */

        testSteps.mostrarCategoriaEnLista(0, "Electronics");
        testSteps.pulsarCategoriaEnLista(0);
        //testSteps.iniciarPantallaDeListaDeProductos(0);
        testSteps.mostrarListaDeProductos(2);
        testSteps.mostrarProductoEnLista(0, "Smartphone");

        /*
        ViewInteraction recyclerView3 =
            onView(new RecyclerViewMatcher(R.id.product_recycler)
                .atPositionOnView(1, R.id.product_name))
                .check(matches(withText("Laptop")));
        recyclerView3.perform(click());


        ViewInteraction textView15 = onView(allOf(
            withId(R.id.product_detail),
            isDisplayed()
        ));
        textView15.check(matches(withText(
            "High performance laptop for gaming and work"
        )));
        */

        testSteps.mostrarProductoEnLista(1, "Laptop");
        testSteps.pulsarProductoEnLista(1);
        //testSteps.iniciarPantallaDeDetalleDelProducto(1);
        testSteps.mostrarDetalleProducto(
            "High performance laptop for gaming and work"
        );

        /*
        pressBack();


        onView(new RecyclerViewMatcher(R.id.product_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("Smartphone")));
        */

        testSteps.pulsarBackEnDetalleDeProductos();
        testSteps.mostrarProductoEnLista(0, "Smartphone");
        testSteps.pulsarProductoEnLista(0);
        //testSteps.iniciarPantallaDeDetalleDelProducto(0);
        testSteps.mostrarDetalleProducto(
            "Latest model with high resolution camera"
        );

        /*
        pressBack();


        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(0, R.id.category_name))
            .check(matches(withText("Electronics")));

        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(1, R.id.category_name))
            .check(matches(withText("Books")));

        */

        testSteps.pulsarBackEnListaDeProductos();
        testSteps.mostrarListaDeCategorias(4);
        testSteps.mostrarCategoriaEnLista(0, "Electronics");
        testSteps.mostrarCategoriaEnLista(1, "Books");
        testSteps.mostrarCategoriaEnLista(2, "Fashion");
    }

}
