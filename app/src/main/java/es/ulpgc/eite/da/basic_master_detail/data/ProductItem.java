package es.ulpgc.eite.da.basic_master_detail.data;

import android.icu.util.ULocale;

import java.io.Serializable;

public class ProductItem implements Serializable {
    private int id;
    private String name;
    private String description;
    private CategoryItem category;

    public ProductItem(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public CategoryItem getCategory() { return category; }


    public void setCategory(CategoryItem category) {
        this.category = category;
    }


    // Sobrescribir equals y hashCode para comparación

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductItem item = (ProductItem) obj;
        return item.id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

}