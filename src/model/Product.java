package model;

public class Product {
    private int productID;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private double productRating;
    private int productSold;
    private String detailBarang;

    public Product(int productID, String productName, int productQuantity, double productPrice, double productRating,
            int productSold, String detailBarang) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productRating = productRating;
        this.productSold = productSold;
        this.detailBarang = detailBarang;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    public int getProductSold() {
        return productSold;
    }

    public void setProductSold(int productSold) {
        this.productSold = productSold;
    }

    public String getDetailBarang() {
        return detailBarang;
    }

    public void setDetailBarang(String detailBarang) {
        this.detailBarang = detailBarang;
    }
}