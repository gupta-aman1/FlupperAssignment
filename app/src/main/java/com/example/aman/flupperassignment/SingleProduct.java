package com.example.aman.flupperassignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleProduct extends AppCompatActivity {
    @BindView(R.id.imageview)
    ImageView imageview;
    String name="",desc="",price="",color="",saleprice="",id="";
    byte[] getImage;
    Bitmap bitmap;
    @BindView(R.id.pname)
    TextView pname;
    @BindView(R.id.price)
    TextView rprice;
    @BindView(R.id.desc)
    TextView pdesc;
    @BindView(R.id.color)
    TextView pcolor;
    boolean isImageFitToScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            name = extras.getString("name");
            desc = extras.getString("desc");
            price = extras.getString("price");
            color = extras.getString("color");
            saleprice = extras.getString("sprice");
            getImage = getIntent().getByteArrayExtra("image");
            bitmap = BitmapFactory.decodeByteArray(getImage, 0, getImage.length);
            imageview.setImageBitmap(bitmap);
            pname.setText(name);
            rprice.setText("Rs. "+saleprice);
            pdesc.setText(desc);
            pcolor.setText("Color : "+color);
            getSupportActionBar().setTitle(name);
//            create.setText("Update Product");
//            Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, ""+desc, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, ""+price, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, ""+saleprice, Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
    }
//    @OnClick(R.id.imageview)
//    public void imageClick()
//    {
//        if(isImageFitToScreen) {
//                    isImageFitToScreen=false;
//                    imageview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                      imageview.setAdjustViewBounds(true);
//                    isImageFitToScreen=false;
//                }else{
//                    isImageFitToScreen=true;
//                    imageview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
//                }
//    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SingleProduct.this,ShowProduct.class));
        finish();
    }
}
