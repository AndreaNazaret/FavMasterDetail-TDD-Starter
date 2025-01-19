package es.ulpgc.eite.da.basic_master_detail.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ulpgc.eite.da.basic_master_detail.R;
import es.ulpgc.eite.da.basic_master_detail.data.CategoryItem;

public class CategoryListAdapter
    extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<CategoryItem> categoryList;
    private final View.OnClickListener clickListener;

    public CategoryListAdapter(
        View.OnClickListener listener, List<CategoryItem> categories
    ) {
        clickListener = listener;
        this.categoryList = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItem category = categoryList.get(position);
        holder.categoryName.setText(category.getName());
        holder.itemView.setTag(category);
        holder.itemView.setOnClickListener(clickListener);

    }

    public void addItems(List<CategoryItem> categories) {
        categoryList.addAll(categories);
        notifyDataSetChanged();
    }

    public void setItems(List<CategoryItem> categories) {
        categoryList.clear();
        categoryList.addAll(categories);
        notifyDataSetChanged();
    }

    public void addItem(CategoryItem category) {
        categoryList.add(category);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public CategoryItem getItem(int position) {
        return categoryList.get(position);
    }


    @Override
    public long getItemId(int position) {
        CategoryItem category = categoryList.get(position);
        return category.getId();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
        }
    }
}
