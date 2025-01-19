package es.ulpgc.eite.da.basic_master_detail;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.basic_master_detail.categories.CategoryListActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MasterDetailFavEspressoTests {

    @Rule
    public ActivityScenarioRule<CategoryListActivity> testRule =
        new ActivityScenarioRule<>(CategoryListActivity.class);

    @Test
    public void extraTest() {

        // mostrar lista de categorias

        onView(new RecyclerViewMatcher(R.id.category_list_recycler)
            .atPositionOnView(0, R.id.category_name))
            .check(matches(withText("Electronics")));

        onView(new RecyclerViewMatcher(R.id.category_list_recycler)
            .atPositionOnView(1, R.id.category_name))
            .check(matches(withText("Books")));

        onView(new RecyclerViewMatcher(R.id.category_list_recycler)
            .atPositionOnView(2, R.id.category_name))
            .check(matches(withText("Fashion")));

        onView(new RecyclerViewMatcher(R.id.category_list_recycler)
            .atPositionOnView(3, R.id.category_name))
            .check(matches(withText("Home Appliances")));


        // seleccionar categoria

        ViewInteraction recyclerView1 =
            onView(new RecyclerViewMatcher(R.id.category_list_recycler)
                .atPositionOnView(2, R.id.category_name))
                .check(matches(withText("Fashion")));
        recyclerView1.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de productos

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("T-shirt")));

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(1, R.id.product_name))
            .check(matches(withText("Jeans")));

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(2, R.id.product_name))
            .check(matches(withText("Sneakers")));


        // seleccionar producto

        ViewInteraction recyclerView2 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(1, R.id.product_name))
                .check(matches(withText("Jeans")));
        recyclerView2.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView6 = onView(allOf(
            withId(R.id.product_name),
            withText("Jeans"),
            isDisplayed()
        ));
        textView6.check(matches(withText("Jeans")));

        ViewInteraction imageButton = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton.check(matches(withDrawable(R.drawable.ic_black_heart)));

        // incluir producto en favoritos

        ViewInteraction appCompatImageButton = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        appCompatImageButton.perform(click());
        rotateScreen();
        imageButton.check(matches(withDrawable(R.drawable.ic_red_heart)));

        // mostrar lista de productos
        pressBack();
        rotateScreen();

        // seleccionar producto

        ViewInteraction recyclerView3 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(2, R.id.product_name))
                .check(matches(withText("Sneakers")));
        recyclerView3.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView7 = onView(allOf(
            withId(R.id.product_name),
            withText("Sneakers"),
            isDisplayed()
        ));
        textView7.check(matches(withText("Sneakers")));

        ViewInteraction imageButton2 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton2.check(matches(withDrawable(R.drawable.ic_black_heart)));

        // incluir producto en favoritos

        ViewInteraction appCompatImageButton2 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        appCompatImageButton2.perform(click());
        rotateScreen();
        imageButton2.check(matches(withDrawable(R.drawable.ic_red_heart)));


        // mostrar lista de productos
        pressBack();

        // Add a delay to allow the previous screen can be resumed
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de categorias
        pressBack();
        rotateScreen();

        // seleccionar categoria

        ViewInteraction recyclerView4 =
            onView(new RecyclerViewMatcher(R.id.category_list_recycler)
                .atPositionOnView(0, R.id.category_name))
                .check(matches(withText("Electronics")));
        recyclerView4.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de productos

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("Smartphone")));

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(1, R.id.product_name))
            .check(matches(withText("Laptop")));


        // seleccionar producto

        ViewInteraction recyclerView5 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(0, R.id.product_name))
                .check(matches(withText("Smartphone")));
        recyclerView5.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView10 = onView(allOf(
            withId(R.id.product_name),
            withText("Smartphone"),
            isDisplayed()
        ));
        textView10.check(matches(withText("Smartphone")));

        ViewInteraction imageButton3 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton3.check(matches(withDrawable(R.drawable.ic_black_heart)));

        // incluir producto en favoritos

        ViewInteraction appCompatImageButton3 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        appCompatImageButton3.perform(click());
        rotateScreen();
        imageButton3.check(matches(withDrawable(R.drawable.ic_red_heart)));


        // mostrar lista de productos
        pressBack();

        // Add a delay to allow the previous screen can be resumed
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de categorias
        pressBack();
        rotateScreen();

        // seleccionar categoria

        ViewInteraction recyclerView6 =
            onView(new RecyclerViewMatcher(R.id.category_list_recycler)
                .atPositionOnView(2, R.id.category_name))
                .check(matches(withText("Fashion")));
        recyclerView6.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de productos

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("T-shirt")));

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(1, R.id.product_name))
            .check(matches(withText("Jeans")));

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(2, R.id.product_name))
            .check(matches(withText("Sneakers")));


        // seleccionar producto

        ViewInteraction recyclerView8 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(1, R.id.product_name))
                .check(matches(withText("Jeans")));
        recyclerView8.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView16 = onView(allOf(
            withId(R.id.product_name),
            withText("Jeans"),
            isDisplayed()
        ));
        textView16.check(matches(withText("Jeans")));

        ViewInteraction imageButton5 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton5.check(matches(withDrawable(R.drawable.ic_red_heart)));

        // mostrar lista de productos
        pressBack();
        rotateScreen();

        // seleccionar producto

        ViewInteraction recyclerView9 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(2, R.id.product_name))
                .check(matches(withText("Sneakers")));
        recyclerView9.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView17 = onView(allOf(
            withId(R.id.product_name),
            withText("Sneakers"),
            isDisplayed()
        ));
        textView17.check(matches(withText("Sneakers")));

        ViewInteraction imageButton12 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton12.check(matches(withDrawable(R.drawable.ic_red_heart)));

        // mostrar lista de productos
        pressBack();

        // Add a delay to allow the previous screen can be resumed
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de categorias
        pressBack();
        rotateScreen();


        // seleccionar categoria

        ViewInteraction recyclerView10 =
            onView(new RecyclerViewMatcher(R.id.category_list_recycler)
                .atPositionOnView(0, R.id.category_name))
                .check(matches(withText("Electronics")));
        recyclerView10.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de productos

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(0, R.id.product_name))
            .check(matches(withText("Smartphone")));

        onView(new RecyclerViewMatcher(R.id.product_list_recycler)
            .atPositionOnView(1, R.id.product_name))
            .check(matches(withText("Laptop")));


        // seleccionar producto

        ViewInteraction recyclerView11 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(0, R.id.product_name))
                .check(matches(withText("Smartphone")));
        recyclerView11.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView20 = onView(allOf(
            withId(R.id.product_name),
            withText("Smartphone"),
            isDisplayed()
        ));
        textView20.check(matches(withText("Smartphone")));

        ViewInteraction imageButton13 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton13.check(matches(withDrawable(R.drawable.ic_red_heart)));

        // eliminar producto de favoritos

        ViewInteraction appCompatImageButton5 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        appCompatImageButton5.perform(click());
        rotateScreen();
        imageButton13.check(matches(withDrawable(R.drawable.ic_black_heart)));

        // mostrar lista de productos
        pressBack();

        // Add a delay to allow the previous screen can be resumed
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de categorias
        pressBack();
        rotateScreen();


        // seleccionar categoria

        ViewInteraction recyclerView12 =
            onView(new RecyclerViewMatcher(R.id.category_list_recycler)
                .atPositionOnView(0, R.id.category_name))
                .check(matches(withText("Electronics")));
        recyclerView12.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // seleccionar producto

        ViewInteraction recyclerView14 =
            onView(new RecyclerViewMatcher(R.id.product_list_recycler)
                .atPositionOnView(0, R.id.product_name))
                .check(matches(withText("Smartphone")));
        recyclerView14.perform(click());

        // Add a delay to allow the next screen can be started
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar producto

        ViewInteraction textView21 = onView(allOf(
            withId(R.id.product_name),
            withText("Smartphone"),
            isDisplayed()
        ));
        textView21.check(matches(withText("Smartphone")));

        ViewInteraction imageButton23 = onView(allOf(
            withId(R.id.favorite_button),
            isDisplayed()
        ));
        rotateScreen();
        imageButton23.check(matches(withDrawable(R.drawable.ic_black_heart)));

        // mostrar lista de productos
        pressBack();

        // Add a delay to allow the previous screen can be resumed
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // mostrar lista de categorias
        pressBack();
    }


    private void rotateScreen()  {

        try {

            UiDevice device = UiDevice.getInstance(
                InstrumentationRegistry.getInstrumentation());

            // Rotate to landscape
            device.setOrientationLeft();

            // Add a delay to allow the screen rotation to complete
            Thread.sleep(1000);

            // Rotate back to portrait
            device.setOrientationNatural();

            // Add a delay to allow the screen rotation to complete
            Thread.sleep(1000);

            // Reset orientation to natural to avoid affecting other tests
            //device.unfreezeRotation();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static Matcher<View> withDrawable(final int resourceId) {

        return new BoundedMatcher<View, ImageButton>(ImageButton.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable resource " + resourceId);
            }

            @Override
            protected boolean matchesSafely(ImageButton imageButton) {
                if (resourceId < 0) {
                    return imageButton.getDrawable() == null;
                }
                Context context = imageButton.getContext();
                Drawable expectedDrawable =
                    AppCompatResources.getDrawable(context, resourceId);
                Drawable actualDrawable = imageButton.getDrawable();

                if (expectedDrawable == null || actualDrawable == null) {
                    return false;
                }

                Bitmap bitmap = getBitmap(actualDrawable);
                Bitmap otherBitmap = getBitmap(expectedDrawable);
                return bitmap.sameAs(otherBitmap);
            }

            private Bitmap getBitmap(Drawable drawable) {
                Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888
                );
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return bitmap;
            }
        };
    }
}
