package com.aayushh.profilepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class shopDetails extends AppCompatActivity implements ShopListAdapter.ViewHolder.OnShopListener {
    private static final String TAG = "";
    RecyclerView recyclerView;
    int image;
    ImageButton imageButton;
//    private SoulpaintDB soulpaintDB;
    private StorageReference firebaseStorage;
    private DatabaseReference databaseReference;
    private SoulpaintsManager soulpaintsManager;
    private SQLiteDatabase sqLiteDatabase;
    List<ShoplistModel> shoplistModelList = new ArrayList<>();
    String[] columns = new String[] {"_ID", "paintingName", "description", "imgLocation", "price"};
    int[] images = {R.drawable.madonna, R.drawable.bighorn, R.drawable.creation, R.drawable.design, R.drawable.goyu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Items");
        firebaseStorage = FirebaseStorage.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    if (snapshot1.child("Name").getValue().toString().equals("Madonna & Child")) {
                        image = R.drawable.madonna;
                    } else if (snapshot1.child("Name").getValue().toString().equals("Bighorn")) {
                        image = R.drawable.bighorn;
                    } else if (snapshot1.child("Name").getValue().toString().equals("The Creation") ) {
                        image = R.drawable.creation;
                    } else if (snapshot1.child("Name").getValue().toString().equals("Design for a Stage")) {
                        image = R.drawable.design;
                    } else if (snapshot1.child("Name").getValue().toString().equals("Goyu")) {
                        image = R.drawable.goyu;
                    } else {
                        image = R.drawable.pattern;
                    }
                    shoplistModelList.add(new ShoplistModel( image, snapshot1.child("Name").getValue().toString(), snapshot1.child("Description").getValue().toString(), snapshot1.child("Price").getValue().toString()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        soulpaintsManager = new SoulpaintsManager(this);
        soulpaintsManager.open();

//        soulpaintsManager.insert("Madonna & Child", "R.drawable.madonna", "Beautifully preserved, this small picture epitomises the finest works produced in Bellini’s workshop for private devotion.", "15000");
//        soulpaintsManager.insert("Bighorn", "R.drawable.bighorn", "Bighorn, from the Quadrupeds Series for Allen and Ginter Cigarettes", "12000");
//        soulpaintsManager.insert("The Creation", "R.drawable.creation", "No description", "20000");
//        soulpaintsManager.insert("Design for a Stage", "R.drawable.design", "Design for a Stage Set at the Opera, Paris ", "18000");
//        soulpaintsManager.insert("Goyu", "R.drawable.goyu", "Asian Art ca. 1835 ", "21000");
//        soulpaintDB = new SoulpaintDB(this);
//
//        try {
//            soulpaintDB.updateDataBase();
//        } catch (IOException mIOException) {
//            throw new Error("UnableToUpdateDatabase");
//        }
//
//        try {
//            sqLiteDatabase = soulpaintDB.getWritableDatabase();
//        } catch (SQLException mSQLException) {
//            throw mSQLException;
//        }

//        Cursor cursor = sqLiteDatabase.query("Items", columns, null, null, null, null, null);
//        while(cursor.moveToNext()) {
//            int loc = Integer.parseInt(cursor.getString(3));
//            int price = Integer.parseInt(cursor.getString(4));
//            shoplistModelList.add(new ShoplistModel(loc, cursor.getString(1), cursor.getString(2), price));
//        }

//        Cursor cursor = soulpaintsManager.fetch();
//        while (cursor.moveToNext()) {
//            int i = 0;
//            int loc = images[i];
//            i++;
//            int price = Integer.parseInt(cursor.getString(4));
//            shoplistModelList.add(new ShoplistModel(loc, cursor.getString(1), cursor.getString(3), price));
//        }
//        shoplistModelList.add(new ShoplistModel(images[0], "Madonna & Child", "Description", 15000));
//        shoplistModelList.add(new ShoplistModel(images[1], "Bighorn", "No Description", 12000));
//        shoplistModelList.add(new ShoplistModel(images[2], "The Creation", "CA. 1857", 20000));
//        shoplistModelList.add(new ShoplistModel(images[3], "Design for a Stage", "Design for a Stage in the Opera, Paris", 19000));
//        shoplistModelList.add(new ShoplistModel(images[4], "Goyu", "Asian Art", 21000));
        ShopListAdapter shopListAdapter = new ShopListAdapter(shoplistModelList, this);

        recyclerView =(RecyclerView) findViewById(R.id.shopRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(shopListAdapter);

        imageButton = (ImageButton) findViewById(R.id.backButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shopDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onShopClick(int position) {
        Intent intent = new Intent(shopDetails.this, ProductDetails.class);
        String name = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textView5)).getText().toString().toUpperCase();
        intent.putExtra("name", name);
        String description = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.shopDesc)).getText().toString();
        String Price =((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.price)).getText().toString();        intent.putExtra("price", Price);
        startActivity(intent);
    }
}