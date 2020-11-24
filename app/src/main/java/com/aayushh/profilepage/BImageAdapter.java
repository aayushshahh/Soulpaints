package com.aayushh.profilepage;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BImageAdapter extends RecyclerView.Adapter<BImageAdapter.ViewHolder> {

    public List<BImageModel> imageModelList;
    public BImageAdapter(List<BImageModel> imageModels) {
        imageModelList = imageModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View bIView = inflater.inflate(R.layout.hometabs, parent, false);
        BImageAdapter.ViewHolder viewHolder = new BImageAdapter.ViewHolder(bIView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BImageModel bimodel = imageModelList.get(position);
        ImageView iView = holder.imageView;
        iView.setImageResource(bimodel.getImgsrc());
        TextView tView = holder.textView;
        tView.setText(bimodel.getTitle());

    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.bImage);
            textView = (TextView) itemView.findViewById(R.id.bImageDesc);
        }

        public ImageView getImageView(){return imageView;}
        public TextView getTextView(){return textView;}
    }
}
