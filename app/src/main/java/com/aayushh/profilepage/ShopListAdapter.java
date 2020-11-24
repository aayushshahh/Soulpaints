package com.aayushh.profilepage;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {
    List<ShoplistModel> shoplistModelList;
    private ViewHolder.OnShopListener mOnShopListener;

    public ShopListAdapter(List<ShoplistModel> modelList, ViewHolder.OnShopListener onShopListener) {
        this.shoplistModelList = modelList;
        this.mOnShopListener = onShopListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View bIView = inflater.inflate(R.layout.shop_layout, parent, false);
        ShopListAdapter.ViewHolder viewHolder = new ShopListAdapter.ViewHolder(bIView, mOnShopListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoplistModel shoplistModel = shoplistModelList.get(position);
        ImageView iView = holder.imageView;
        iView.setImageResource(shoplistModel.getImgsrc());
        TextView tView = holder.textView;
        tView.setText(shoplistModel.getPaintingName());
        TextView tView1 = holder.textView1;
        tView1.setText(shoplistModel.getDescription());
        TextView tView2 = holder.textView2;
        String priceTag = "RS. " + shoplistModel.getPrice();
        tView2.setText(priceTag);

    }

    @Override
    public int getItemCount() {
        return shoplistModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView, textView1, textView2;
        OnShopListener onShopListener;
        public ViewHolder(@NonNull View itemView, OnShopListener onShopListener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView2);
            textView = (TextView) itemView.findViewById(R.id.textView5);
            textView1 = (TextView) itemView.findViewById(R.id.shopDesc);
            textView2 = (TextView) itemView.findViewById(R.id.price);
            this.onShopListener = onShopListener;
            itemView.setOnClickListener(this);
        }
        public ImageView getImageView(){return imageView;}
        public TextView getTextView(){return textView;}
        public TextView getTextView1() {return textView1;}
        public TextView getTextView2() {return textView2;}

        @Override
        public void onClick(View v) {
            onShopListener.onShopClick(getAdapterPosition());
        }

        public interface OnShopListener{
            void onShopClick(int position);
        }
    }
}
