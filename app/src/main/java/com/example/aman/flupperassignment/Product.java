package com.example.aman.flupperassignment;

 // Creation Of Model Class


public class Product
{
    public String id,name,description,regularPrice,salePrice,colors;
    public byte[] productImage;

    public  Product(String id, String name, String description, String regularPrice,String salePrice,
                        String colors,byte[] productImage)
//    public void Product(String id, String name, String description, String regularPrice, String salePrice, String productImage,
//                        String colors)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.regularPrice=regularPrice;
        this.salePrice=salePrice;
        this.colors=colors;
        this.productImage=productImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }


}
