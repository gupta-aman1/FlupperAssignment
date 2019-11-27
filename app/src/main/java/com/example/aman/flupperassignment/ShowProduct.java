package com.example.aman.flupperassignment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowProduct extends AppCompatActivity {
//TextView show;
//ImageView pimage;
@BindView(R.id.prorecycler)
RecyclerView prorecycler;
    DatabaseHelper myDb;
    Bitmap bmp;
    private ProductAdapter mAdapter;
    private ArrayList<Product> productList = new ArrayList<>();
    String id="",name="",desc="",color="",regularPrice="",salePrice="";
    byte[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        ButterKnife.bind(this);

        myDb =  new DatabaseHelper(this);
        getData();

    }

    public void getData()
    {

        Cursor result = myDb.getAllProduct();
        if(result.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext())
        {
             id = result.getString(0);
             name = result.getString(1);
             desc = result.getString(2);
             regularPrice = result.getString(3);
             color = result.getString(4);
            image = result.getBlob(5);
            salePrice = result.getString(6);
            mAdapter = new ProductAdapter(ShowProduct.this,pro());
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            prorecycler.setLayoutManager(layoutManager);
           // prorecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            prorecycler.setAdapter(mAdapter);
           // Product m1 = new Product(id, name, desc, price,color);
    //        buffer.append("Id :"+result.getString(0)+"\n");
    //        buffer.append("Name :"+result.getString(1)+"\n");
    //        buffer.append("Desc :"+result.getString(2)+"\n");
    //        buffer.append("Price :"+result.getString(3)+"\n");
    //        buffer.append("Color :"+result.getString(4)+"\n");
    //        byte[] image=result.getBlob(5);
     //       bmp= BitmapFactory.decodeByteArray(image,0,image.length);

        }
}

    public ArrayList<Product> pro() {
        Product product = new Product(id, name, desc, regularPrice,salePrice,color,image);
        productList.add(product);
        return productList;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ShowProduct.this,MainScreen.class));
        finish();
    }
}
