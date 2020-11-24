package com.aayushh.profilepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Adapter extends PagerAdapter {
    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.carditem, container, false);
        TextView title, artist, desc, price;
        title = view.findViewById(R.id.title);
        artist = view.findViewById(R.id.artist);
        desc = view.findViewById(R.id.description);
        price = view.findViewById(R.id.price);

        title.setText(models.get(position).getTitle());
        artist.setText(models.get(position).getArtist());
        desc.setText(models.get(position).getDesc());
        price.setText(models.get(position).getPrice());

        container.addView(view, 0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
