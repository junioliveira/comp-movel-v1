package com.example.junio.v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junio.v1.R;
import com.example.junio.v1.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junio on 04/10/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void onBind(Category category) {
            if (category != null) {
                tvId.setText("Id: " + category.getIdCategory());
                tvName.setText("Name: " + category.getName());
            }
        }
    }
}
