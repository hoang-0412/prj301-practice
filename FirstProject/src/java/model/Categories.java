package model;

public class Categories {

    String categoryID, categoryName, describe;

    public Categories() {
    }

    public Categories(String categoryID, String categoryName, String describe) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.describe = describe;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Categories{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + ", describe=" + describe + '}';
    }

}
