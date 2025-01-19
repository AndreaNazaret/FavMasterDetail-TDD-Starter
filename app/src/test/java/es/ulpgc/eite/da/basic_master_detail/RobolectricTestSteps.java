package es.ulpgc.eite.da.basic_master_detail;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Robolectric.buildActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ApplicationProvider;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import es.ulpgc.eite.da.basic_master_detail.categories.CategoryListActivity;
import es.ulpgc.eite.da.basic_master_detail.product.ProductDetailActivity;
import es.ulpgc.eite.da.basic_master_detail.products.ProductListActivity;


@SuppressWarnings("ALL")
@RunWith(RobolectricTestRunner.class)
public class RobolectricTestSteps {

    ActivityController<CategoryListActivity> mainCtrler;
    ActivityController<ProductListActivity> nextCtrler;
    ActivityController<ProductDetailActivity> lastCtrler;

    private CategoryListActivity mainActivity;
    private ProductListActivity nextActivity;
    private ProductDetailActivity lastActivity;


    public void iniciarPantallaDeListaDeCategorias() {

        mainCtrler = buildActivity(CategoryListActivity.class)
            .create().resume().visible();
        mainActivity = mainCtrler.get();
    }

    public void mostrarListaDeCategorias(int size) {

        RecyclerView recyclerView =
            mainActivity.findViewById(R.id.category_list_recycler);
        assertThat(recyclerView.getAdapter().getItemCount(), is(size));
    }


    public void mostrarCategoriaEnLista(int pos, String val) {
        RecyclerView recyclerView =
            mainActivity.findViewById(R.id.category_list_recycler);

        // Necesitamos asegurarnos de que el RecyclerView
        // ha sido dispuesto y los ViewHolders creados
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST)
        );
        recyclerView.layout(0, 0, 1000, 1000);

        // Desplazamos el RecyclerView a la posición deseada
        // para asegurarnos de que el ViewHolder es creado
        recyclerView.scrollToPosition(pos);

        // Obtenemos el ViewHolder en la posición deseada
        RecyclerView.ViewHolder viewHolder =
            recyclerView.findViewHolderForAdapterPosition(pos);

        assertNotNull(viewHolder);

        // Simulamos un clic en el itemView del ViewHolder
        viewHolder.itemView.performClick();

        View targetView = viewHolder.itemView.findViewById(R.id.category_name);
        assertThat(((TextView) targetView).getText().toString(), containsString(val));
    }


    public void pulsarCategoriaEnLista(int pos) {
        RecyclerView recyclerView =
            mainActivity.findViewById(R.id.category_list_recycler);

        // Necesitamos asegurarnos de que el RecyclerView
        // ha sido dispuesto y los ViewHolders creados
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST)
        );
        recyclerView.layout(0, 0, 1000, 1000);

        // Desplazamos el RecyclerView a la posición deseada
        // para asegurarnos de que el ViewHolder es creado
        recyclerView.scrollToPosition(pos);

        // Obtenemos el ViewHolder en la posición deseada
        RecyclerView.ViewHolder viewHolder =
            recyclerView.findViewHolderForAdapterPosition(pos);

        assertNotNull(viewHolder);

        // Simulamos un clic en el itemView del ViewHolder
        viewHolder.itemView.performClick();

        /*if (nextActivity == null) {

            // Verificamos que la nueva actividad ha sido iniciada
            nextCtrler = Robolectric
                .buildActivity(ProductListActivity.class)
                .create().resume().visible();
            nextActivity = nextCtrler.get();

        }*/

        Intent intent = new Intent(
            //ApplicationProvider.getApplicationContext(),
            mainActivity,
            ProductListActivity.class
        );
        intent.putExtra(
            "category_id",
            (int) recyclerView.getAdapter().getItemId(pos)
        );


        mainCtrler.pause();

        nextCtrler = buildActivity(ProductListActivity.class, intent)
            .create().resume().visible();
        nextActivity = nextCtrler.get();
    }

    /*
    public void iniciarPantallaDeListaDeProductos(int pos) {

        RecyclerView recyclerView =
            mainActivity.findViewById(R.id.category_list_recycler);

        Intent intent = new Intent(
            //ApplicationProvider.getApplicationContext(),
            mainActivity,
            ProductListActivity.class
        );
        intent.putExtra(
            "category_id",
            (int) recyclerView.getAdapter().getItemId(pos)
        );


        mainCtrler.pause();

        nextCtrler = buildActivity(ProductListActivity.class, intent)
            .create().resume().visible();
        nextActivity = nextCtrler.get();
    }
    */

    public void mostrarListaDeProductos(int size) {

        assertNotNull(nextActivity);

        RecyclerView recyclerView =
            nextActivity.findViewById(R.id.product_list_recycler);
        assertThat(recyclerView.getAdapter().getItemCount(), is(size));
    }


    public void mostrarProductoEnLista(int pos, String val) {

        assertNotNull(nextActivity);

        RecyclerView recyclerView =
            nextActivity.findViewById(R.id.product_list_recycler);


        // Necesitamos asegurarnos de que el RecyclerView
        // ha sido dispuesto y los ViewHolders creados
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST)
        );
        recyclerView.layout(0, 0, 1000, 1000);

        // Desplazamos el RecyclerView a la posición deseada
        // para asegurarnos de que el ViewHolder es creado
        recyclerView.scrollToPosition(pos);

        //shadowOf(getMainLooper()).idle();

        // Obtenemos el ViewHolder en la posición deseada
        RecyclerView.ViewHolder viewHolder =
            recyclerView.findViewHolderForAdapterPosition(pos);

        assertNotNull(viewHolder);

        View targetView = viewHolder.itemView.findViewById(R.id.product_name);
        assertThat(((TextView) targetView).getText().toString(), containsString(val));

    }


    public void mostrarDetalleProducto(String text) {

        assertNotNull(lastActivity);

        TextView targetView = lastActivity.findViewById(R.id.product_description);
        assertThat(((TextView) targetView).getText().toString(), containsString(text));

    }

    public void pulsarProductoEnLista(int pos) {

        assertNotNull(nextActivity);

        RecyclerView recyclerView =
            nextActivity.findViewById(R.id.product_list_recycler);

        // Necesitamos asegurarnos de que el RecyclerView
        // ha sido dispuesto y los ViewHolders creados
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST)
        );
        recyclerView.layout(0, 0, 1000, 1000);


        // Desplazamos el RecyclerView a la posición deseada
        // para asegurarnos de que el ViewHolder es creado
        recyclerView.scrollToPosition(pos);

        // Obtenemos el ViewHolder en la posición deseada
        RecyclerView.ViewHolder viewHolder =
            recyclerView.findViewHolderForAdapterPosition(pos);

        assertNotNull(viewHolder);

        // Simulamos un clic en el itemView del ViewHolder
        viewHolder.itemView.performClick();

        /*if (lastActivity == null) {

            // Verificamos que la nueva actividad ha sido iniciada
            ActivityController<ProductDetailActivity> controller =
                buildActivity(ProductDetailActivity.class)
                    .create().resume().visible();
            lastActivity = controller.get();
        }*/


        Intent intent = new Intent(
            //ApplicationProvider.getApplicationContext(),
            nextActivity,
            ProductDetailActivity.class
        );
        intent.putExtra(
            "product_id",
            (int) recyclerView.getAdapter().getItemId(pos)
        );


        nextCtrler.pause();

        lastCtrler = buildActivity(ProductDetailActivity.class, intent)
            .create().resume().visible();
        lastActivity = lastCtrler.get();

    }

    /*
    public void iniciarPantallaDeDetalleDelProducto(int pos) {

        RecyclerView recyclerView =
            nextActivity.findViewById(R.id.product_list_recycler);

        Intent intent = new Intent(
            //ApplicationProvider.getApplicationContext(),
            nextActivity,
            ProductDetailActivity.class
        );
        intent.putExtra(
            "product_id",
            (int) recyclerView.getAdapter().getItemId(pos)
        );


        nextCtrler.pause();

        lastCtrler = buildActivity(ProductDetailActivity.class, intent)
            .create().resume().visible();
        lastActivity = lastCtrler.get();
    }
    */

    public void pulsarBackEnListaDeProductos() {
        assertNotNull(nextActivity);

        nextActivity.onBackPressed();
        //nextActivity = null;
        //nextCtrler = null;
        nextCtrler.destroy();
        mainCtrler.resume();
    }

    public void pulsarBackEnDetalleDeProductos() {
        assertNotNull(lastActivity);

        lastActivity.onBackPressed();
        //lastActivity = null;
        lastCtrler.destroy();
        nextCtrler.resume();
    }

    public void rotarPantalla() {
        mainActivity.recreate();
    }
}
