package models;

public interface LibraryManager {
    void addItem();
    void deleteItem(String isbn);
    void displayItemsList();
    void borrowItem(String isbn);
    void returnItem();
    void generateReport();
    void makeReservation();

}
