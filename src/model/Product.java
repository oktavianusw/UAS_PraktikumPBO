package model;
import java.util.ArrayList;

public class Product {
    private int productID;
    private String productName;
    private String productQuantity;
    private String productPrice;
    private double productRating;
    private int productSold;
    private ArrayList <String> detailBarang = new ArrayList<>();
    public Product(int productID, String productName, String productQuantity, String productPrice, double productRating, int productSold, ArrayList<String> detailBarang) {
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
        this.productName= productName;
    }
    public String getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
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
    public ArrayList<String> getDetailBarang() {
        return detailBarang;
    }
    public void setDetailBarang(ArrayList<String> detailBarang) {
        this.detailBarang = detailBarang;
    }
}
