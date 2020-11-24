package com.aayushh.profilepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    ImageView imageView;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("desc");
        String price = intent.getStringExtra("price");

        models = new ArrayList<>();
        models.add(new Model(name, "Giovanni Bellini", description, price));
        models.add(new Model(name, "Giovanni Bellini", description, price));
        imageView = (ImageView) findViewById(R.id.productImage);

        if(name.equals("MADONNA & CHILD")) {
            imageView.setImageResource(R.drawable.madonna);
        } else if (name.equals("BIGHORN")) {
            imageView.setImageResource(R.drawable.bighorn);
        } else if (name.equals("THE CREATION")) {
            imageView.setImageResource(R.drawable.creation);
        } else if (name.equals("CREATION FOR A STAGE")) {
            imageView.setImageResource(R.drawable.design);
        } else if (name.equals("GOYU")) {
            imageView.setImageResource(R.drawable.goyu);
        } else if (name.equals("ABSTRACT 3")) {
            imageView.setImageResource(R.drawable.abstract3);
        } else if (name.equals("MODERN 1")) {
            imageView.setImageResource(R.drawable.modern1);
        } else if (name.equals("LANDSCAPE 1")) {
            imageView.setImageResource(R.drawable.landscape1);
        } else {
            imageView.setImageResource(R.drawable.goyu);
        }

        button = (ImageButton) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, shopDetails.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.cardPager);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < adapter.getCount() - 1) {

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}