package com.example.aman.flupperassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>  {
    private Context context;
    private ArrayList<Product> productList;
    DatabaseHelper myDb;
    boolean isImageFitToScreen;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.showproducts, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        myDb =  new DatabaseHelper(context);
        holder.proname.setText(productList.get(position).getName());
        holder.proprice.setText("Rs. "+productList.get(position).getRegularPrice());
        byte[] recordImage = productList.get(position).getProductImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.proimage.setImageBitmap(bitmap);
        holder.prosprice.setText("Rs. "+productList.get(position).getSalePrice());


 holder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       Integer deletedRows= myDb.deleteData(productList.get(position).getId());
       productList.remove(position).getId();
       notifyItemRemoved(position);
       if(deletedRows>0)
       {
           Toast.makeText(context, "Product Deleted Succesfully", Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(context, "Product not Deleted", Toast.LENGTH_SHORT).show();
       }

    }
});

 holder.edit.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         //Intent
         context.startActivity(new Intent(context,CreateProduct.class)
                 .putExtra("id",productList.get(position).getId())
                 .putExtra("name",productList.get(position).getName())
                 .putExtra("desc",productList.get(position).getDescription())
                 .putExtra("price",productList.get(position).getRegularPrice())
                 .putExtra("color",productList.get(position).getColors())
                 .putExtra("sprice",productList.get(position).getSalePrice())
                 .putExtra("image",productList.get(position).getProductImage()));
                ((Activity)context).finish();
     }
 });

//        holder.proimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isImageFitToScreen) {
//                    isImageFitToScreen=false;
//                    holder.proimage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                    holder.proimage.setAdjustViewBounds(true);
//                    isImageFitToScreen=false;
//                }else{
//                    isImageFitToScreen=true;
//                    holder.proimage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//                    holder.proimage.setScaleType(ImageView.ScaleType.FIT_XY);
//                }
//            }
//        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,SingleProduct.class)
                        .putExtra("id",productList.get(position).getId())
                        .putExtra("name",productList.get(position).getName())
                        .putExtra("desc",productList.get(position).getDescription())
                        .putExtra("price",productList.get(position).getRegularPrice())
                        .putExtra("color",productList.get(position).getColors())
                        .putExtra("sprice",productList.get(position).getSalePrice())
                        .putExtra("image",productList.get(position).getProductImage()));
                ((Activity)context).finish();
            }
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView proname,prosprice,proprice;
        public ImageView proimage,delete,edit;
        CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            proname = view.findViewById(R.id.proname);
            proimage = view.findViewById(R.id.proimage);
            proprice = view.findViewById(R.id.proprice);
            prosprice = view.findViewById(R.id.prosprice);
            delete = view.findViewById(R.id.delete);
            edit = view.findViewById(R.id.edit);
            cardView = view.findViewById(R.id.cardview);
        }
    }
}
