package com.example.aman.flupperassignment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProduct extends AppCompatActivity {
    @BindView(R.id.pimage)
    ImageView image;
    @BindView(R.id.clickimage)
    ImageView clickimage;
    @BindView(R.id.pname)
    EditText pname;
    @BindView(R.id.pdesc)
    EditText pdesc;
    @BindView(R.id.pprice)
    EditText pprice;

    @BindView(R.id.sprice)
    EditText sprice;
    @BindView(R.id.create)
    Button create;
    @BindView(R.id.procolor)
    Spinner procolor;

    final private int IMG_REQUEST=1;
    final private int IMG=2;
    Bitmap bitmap;
    Uri imageUri;
    DatabaseHelper myDb;
    String name="",desc="",price="",color="",saleprice="",id="";
    byte[] getImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        ButterKnife.bind(this);
        myDb =  new DatabaseHelper(this);             // Creating Database and Table

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            name = extras.getString("name");
            desc = extras.getString("desc");
            price = extras.getString("price");
            color = extras.getString("color");
            saleprice = extras.getString("sprice");
            getImage = getIntent().getByteArrayExtra("image");
            pname.setText(name);
            pdesc.setText(desc);
            pprice.setText(price);
            sprice.setText(saleprice);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.colors));
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            procolor.setAdapter(dataAdapter);
            if (color != null) {
                int spinnerPosition = dataAdapter.getPosition(color);
                procolor.setSelection(spinnerPosition);
            }
           bitmap = BitmapFactory.decodeByteArray(getImage, 0, getImage.length);
            image.setImageBitmap(bitmap);
            getSupportActionBar().setTitle("Update Product");
            create.setText("Update Product");

        }
        else
        {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.colors));
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            procolor.setAdapter(dataAdapter);
        }
        colorCategoy();
    }

    public void colorCategoy(){
        procolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                color=getResources().getStringArray(R.array.colors)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @OnClick(R.id.create)
    public void getProductData()
    {
        if(create.getText().toString().equals("Create Product"))
        {
            String backgroundImageName = String.valueOf(image.getTag());
            if(pname.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Product Name")

                        .show();
            }
            else if( pdesc.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Product Description")

                        .show();
            }
            else if( pprice.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Regular Price")

                        .show();
            }
            else if( sprice.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Sale Price")

                        .show();
            }
            else if( sprice.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Sale Price")

                        .show();
            }
            else if(backgroundImageName.equals("exist"))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Select/Capture Product Image")

                        .show();
            }
            else {
                boolean isInserted = myDb.insertData(pname.getText().toString().trim(), pdesc.getText().toString().trim()
                        , pprice.getText().toString().trim(),color, imagetostring(bitmap), sprice.getText().toString().trim());
                if (isInserted == true) {
                    Toast.makeText(this, "Product Created Sucessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateProduct.this, MainScreen.class));
                    finish();
                } else
                    Toast.makeText(this, "Something went wrong !!!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {

            String backgroundImageName = String.valueOf(image.getTag());
            if(pname.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Product Name")

                        .show();
            }
            else if( pdesc.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Product Description")

                        .show();
            }
            else if( pprice.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Regular Price")

                        .show();
            }

            else if( sprice.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Sale Price")

                        .show();
            }
            else if( sprice.getText().toString().trim().equals(""))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Input Sale Price")

                        .show();
            }
            else if(backgroundImageName.equals("exist"))
            {
                new AlertDialog.Builder(CreateProduct.this)
                        //.setTitle("Record New Track")
                        .setMessage("Please Select/Capture Product Image")

                        .show();
            }
            else {

                boolean isUpdate = myDb.updateData(id, pname.getText().toString().trim(), pdesc.getText().toString().trim()
                        , pprice.getText().toString().trim(),color, imagetostring(bitmap),
                        sprice.getText().toString().trim());

                if (isUpdate == true) {
                    Toast.makeText(this, "Product Updated Sucessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateProduct.this, MainScreen.class));
                    finish();
                } else
                    Toast.makeText(this, "Something went wrong !!!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @OnClick(R.id.clickimage)
    public void onImageClick(View view) {
        requestStoragePermission();
    }

    private void selectProductImage() {


        final CharSequence[] options = {"Take Photo", "Choose from Gallery"};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add Product Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {


                    captureImage();
                  /*  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 1);*/

                } else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);

                    //if everything is ok we will open image chooser
                   /* Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    i.putExtra("crop","true");
                    i.putExtra("aspectX",1);
                    i.putExtra("aspectY",1);
                    i.putExtra("outputX",200);
                    i.putExtra("outputY",200);
                    i.putExtra("return-data",true);
                 //   i.putExtra("crop","true");
                    startActivityForResult(i, 2);*/


                } /*else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }*/

            }

        }).setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TOdo
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void captureImage(){
        //  Intent intent = new Intent();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //  intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode ==RESULT_OK && data !=null){
            Uri path = data.getData();
            //  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
            image.setTag("set");
        }

        else if(requestCode==IMG && resultCode ==RESULT_OK && data !=null)
        {
            /* Bundle extras  = data.getExtras();
            bitmap =extras.getParcelable("data");
            iv1.setImageBitmap(bitmap);*/
            //getting the image Uri
            imageUri = data.getData();

            try {
                //getting bitmap object from uri
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                //displaying selected image to imageview
                   image.setImageBitmap(bitmap);
                image.setTag("set");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else
        {

        }
    }

    private byte[] imagetostring(Bitmap bitmap)
    {
//        image.setDrawingCacheEnabled(true);
//        image.buildDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=1;

        bitmap=Bitmap.createScaledBitmap(bitmap,600,550, true);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imbytes = byteArrayOutputStream.toByteArray();
        //return Base64.encodeToString(imbytes, Base64.DEFAULT);
        return imbytes;

    }


    private void requestStoragePermission() {
        Dexter.withActivity(CreateProduct.this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            selectProductImage();
                          //  Toast.makeText(CreateProduct.this, "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(CreateProduct.this, "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateProduct.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CreateProduct.this,MainScreen.class));
        finish();
    }
}
