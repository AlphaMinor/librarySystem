package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public abstract class LibraryItem {
    private String type;
    private String isbnNumber;
    private String title;
    private String publicationDate;
    private DateTime dateBorrowed;
    public String dueDate;
    private String currentReader;
    private boolean isAvailableToBorrow;
    ArrayList<Book> allItemsInTheLibrary = new ArrayList<>();
    public static final int MAX_ITEM_COUNT = 150;
    public static int currentItemCount = 0;
    public static int remainingItemCount = MAX_ITEM_COUNT - currentItemCount;
    public static Map<String, String> dueDates = new LinkedHashMap<>();
    private double fee;
    private int borrowedPeriod;


    public LibraryItem() {

    }

    public LibraryItem(  String isbnNumber, String title, String publicationDate) {
//        this.isAvailableToBorrow = isAvailableToBorrow;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.publicationDate = publicationDate;
    }

    public LibraryItem( boolean isAvailableToBorrow, String isbnNumber, String title, String publicationDate) {
        this.isAvailableToBorrow = isAvailableToBorrow;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.publicationDate = publicationDate;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DateTime getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(DateTime dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(String currentReader) {
        this.currentReader = currentReader;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean getIsAvailableToBorrow() {
        return isAvailableToBorrow;
    }

    public void setIsAvailableToBorrow(boolean isAvailableToBorrow) {
        this.isAvailableToBorrow = isAvailableToBorrow;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getBorrowedPeriod() {
        return borrowedPeriod;
    }

    public void setBorrowedPeriod(int borrowedPeriod) {
        this.borrowedPeriod = borrowedPeriod;
    }

    public abstract String getDueDate();

    public abstract void setDueDate();


}
