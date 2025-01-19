package es.ulpgc.eite.da.basic_master_detail.data;

import java.util.ArrayList;
import java.util.List;

public class CatalogRepository {
    private static CatalogRepository instance;
    private List<CategoryItem> categories;

    private CatalogRepository() {
        categories = loadCategories();
    }

    public static CatalogRepository getInstance() {
        if (instance == null) {
            instance = new CatalogRepository();
        }
        return instance;
    }

    private List<CategoryItem> loadCategories() {
        List<CategoryItem> categories = new ArrayList<>();

        // Electronics category
        List<ProductItem> electronicsProducts = new ArrayList<>();
        electronicsProducts.add(new ProductItem(
            1, "Smartphone",
            "Latest model with high resolution camera"
        ));
        electronicsProducts.add(new ProductItem(
            2, "Laptop",
            "High performance laptop for gaming and work"
        ));
        /*electronicsProducts.add(new Product(
            3, "Headphones",
            "Noise-cancelling over-ear headphones"
        ));*/
        CategoryItem electronics = new CategoryItem(
            1, "Electronics",
            "Electronic items",
            electronicsProducts
        );

        // Books category
        List<ProductItem> booksProducts = new ArrayList<>();
        booksProducts.add(new ProductItem(
            4, "The Great Gatsby",
            "A classic novel by F. Scott Fitzgerald"
        ));
        booksProducts.add(new ProductItem(
            5, "1984",
            "Dystopian novel by George Orwell"
        ));
        booksProducts.add(new ProductItem(
            6, "To Kill a Mockingbird",
            "Novel by Harper Lee"
        ));

        //CategoryItem books = new CategoryItem(2, "Books");

        CategoryItem books = new CategoryItem(
            2, "Books",
            "Various kinds of books",
            booksProducts
        );

        // Fashion category
        List<ProductItem> fashionProducts = new ArrayList<>();
        fashionProducts.add(new ProductItem(
            7, "T-shirt",
            "Cotton T-shirt available in various colors"
        ));
        fashionProducts.add(new ProductItem(
            8, "Jeans",
            "Slim fit jeans for casual wear"
        ));
        fashionProducts.add(new ProductItem(
            9, "Sneakers",
            "Comfortable sneakers for daily use"
        ));

        //CategoryItem fashion = new CategoryItem(3, "Fashion");

        CategoryItem fashion = new CategoryItem(
            3, "Fashion",
            "Clothing and accessories",
            fashionProducts
        );

        // Home Appliances category
        List<ProductItem> homeAppliancesProducts = new ArrayList<>();
        homeAppliancesProducts.add(new ProductItem(
            10, "Refrigerator",
            "Energy efficient refrigerator"
        ));
        homeAppliancesProducts.add(new ProductItem(
            11, "Microwave Oven",
            "Compact microwave oven with multiple functions"
        ));
        homeAppliancesProducts.add(new ProductItem(
            12, "Washing Machine",
            "Front load washing machine with quick wash feature"
        ));

        //CategoryItem homeAppliances = new CategoryItem(4, "Home Appliances");

        CategoryItem homeAppliances = new CategoryItem(
            4, "Home Appliances",
            "Appliances for home use",
            homeAppliancesProducts
        );

        // Add categories to list
        categories.add(electronics);
        categories.add(books);
        categories.add(fashion);
        categories.add(homeAppliances);

        // Set category for each product
        for (ProductItem product : electronicsProducts) {
            product.setCategory(electronics);
        }
        for (ProductItem product : booksProducts) {
            product.setCategory(books);
        }
        for (ProductItem product : fashionProducts) {
            product.setCategory(fashion);
        }
        for (ProductItem product : homeAppliancesProducts) {
            product.setCategory(homeAppliances);
        }

        return categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

    public List<ProductItem> getProductsByCategoryId(int categoryId) {
        for (CategoryItem category : categories) {
            if (category.getId() == categoryId) {
                return category.getProducts();
            }
        }

        // return empty list if category not found
        return new ArrayList<>();
    }

    public ProductItem getProductById(int productId) {
        for (CategoryItem category : categories) {
            for (ProductItem product : category.getProducts()) {
                if (product.getId() == productId) {
                    return product;
                }
            }
        }

        // return null if product not found
        return null;
    }
}
