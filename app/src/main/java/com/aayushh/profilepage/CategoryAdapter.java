package com.aayushh.profilepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModel;
    private Context mContext;
    private ViewHolder.OnCategoryListener mOnCategoryListener;

    public CategoryAdapter(List<CategoryModel> categoryModels, Context context, ViewHolder.OnCategoryListener onCategoryListener){
        categoryModel = categoryModels;
        this.mContext = context;
        this.mOnCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.categoryitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(categoryView, mOnCategoryListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel category = categoryModel.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), shopDetails.class);
                v.getContext().startActivity(intent);

            }
        });
        TextView textView = holder.title;
        textView.setText(category.getTitle());

    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        OnCategoryListener onCategoryListener;
        public ViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.categoryTitle);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener(this);
        }

        public TextView getTitle(){
            return title;
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }

        public interface OnCategoryListener{
            void onCategoryClick(int position);
        }
    }
}
