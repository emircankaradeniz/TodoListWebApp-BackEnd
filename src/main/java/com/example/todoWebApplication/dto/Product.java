package com.example.todoWebApplication.dto;

public class Product {
    private String title;
    private String price;
    private String imageUrl;
    private String productUrl;

    public Product(String title, String price, String imageUrl, String productUrl) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
    }

    // Getter ve Setter metodları
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}
