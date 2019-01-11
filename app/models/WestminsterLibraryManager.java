package models;

import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterLibraryManager extends DateTime implements LibraryManager {
    Scanner sc = new Scanner(System.in);
    ArrayList<LibraryItem> borrowedLibraryItems = new ArrayList<>();
    ArrayList<LibraryItem> allLibraryItems = new ArrayList<>();
    ArrayList<LibraryItem> itemsOverdue = new ArrayList<>();
    ArrayList<String> availableLanguages = new ArrayList<>(); //temporary for contructor
    ArrayList<String> availableSubtitles = new ArrayList<>();//temporary for contructor
    ArrayList<String> actors = new ArrayList<>();//temporary for contructor
    boolean availability = true;

    public static void main(String[] args) {

    }

    @Override
    public void addItem() {
        if (LibraryItem.currentItemCount == 150) {
            System.out.println("Sry! Currently no free space available in the library");
            //  displayMainMenu();
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Enter item type(Book/ DVD): ");
        String itemType = sc.next();
        if (itemType.equalsIgnoreCase("Book") && Book.currentBookCount == 100) {
            System.out.println("Sry! Currently no free space available for books in the library");
            // displayMainMenu();
        }
        if (itemType.equalsIgnoreCase("DVD") && DVD.currentDvdCount == 50) {
            System.out.println("Sry! Currently no free space available for DVDs in the library");
            //   displayMainMenu();
        }

        System.out.println("Enter the isbn: ");
        String isbn = sc.next();

        System.out.println("Enter the title: ");
        String title = sc.next();

        System.out.println("Enter the publication date(dd/mm/yy): ");
        String publicationDate = sc.nextLine();
        sc.nextLine();

        if (itemType.equalsIgnoreCase("Book")) {
            Book book = new Book();
            String option = "";

            while (option.equalsIgnoreCase("Y")) {
                System.out.println("Enter author name");
                String author = sc.next();
                book.authors.add(author);
                System.out.println("Are there any more authors(Y/N)? ");
                option = sc.next();
            }

            System.out.println("Enter publisher: ");
            String publisher = sc.nextLine();
            System.out.println("Enter the total no. of pages: ");
            int totalNumberOfPages = sc.nextInt();


//            (String type, boolean isAvailableToBorrow, String isbnNumber, String title, String publicationDate,
//                    ArrayList<String> authors, String publisher, int totalNumberOfPages)
            Book newBook = new Book(availability, isbn, title, publicationDate, book.authors, publisher, totalNumberOfPages);
            Book.allBooksInTheLibrary.add(newBook);
            Book.currentBookCount++;

        } else if (itemType.equalsIgnoreCase("DVD")) {
            DVD dvd = new DVD();
            DVD ddvd = new DVD(false,isbn, title, publicationDate, dvd.availableLanguages, dvd.availableSubtitles, dvd.actors);
            String option = "";

            while (option.equalsIgnoreCase("Y")) {
                System.out.println("Enter available language");
                String language = sc.next();
                ddvd.availableLanguages.add(language);
                System.out.println("Are there any more languages(Y/N)? ");
                option = sc.next();
            }

            while (option.equalsIgnoreCase("Y")) {
                System.out.println("Enter available subtitles");
                String subtitle = sc.next();
                ddvd.availableSubtitles.add(subtitle);
                System.out.println("Are there any more subtitles(Y/N)? ");
                option = sc.next();
            }

            System.out.println("Enter producer: ");
            String producer = sc.nextLine();
            while (option.equalsIgnoreCase("Y")) {
                System.out.println("Enter the actors");
                String actor = sc.next();
                ddvd.actors.add(actor);
                System.out.println("Are there any more actors(Y/N)? ");
                option = sc.next();
            }

            DVD newDVD = new DVD(false,isbn, title, publicationDate, producer, ddvd.availableLanguages, ddvd.availableSubtitles, ddvd.actors);
            DVD.allDVDsInTheLibrary.add(newDVD);
            DVD.currentDvdCount++;
        }

        LibraryItem.currentItemCount++;
        //  displayMainMenu();
    }

    @Override
    public void deleteItem(String ISBN) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Enter the ISBN number: ");
        ISBN = sc.next();
        boolean foundItem = false;

        for (int i = 0; i < Book.allBooksInTheLibrary.size(); i++) {
            if (Book.allBooksInTheLibrary.get(i).getIsbnNumber().equals(ISBN)) {
                foundItem = true;
                Book.allBooksInTheLibrary.remove(i);
                System.out.println("Book Deleted!");
                System.out.println("Totl number of free spaces left in the library: " + LibraryItem.remainingItemCount);
                System.out.println("Number of free spaces left for books: " + Book.remainingBookCount);
                System.out.println("Number of free spaces left for dvds: : " + DVD.remainingDVDCount);
                break;
            }
        }

        for (int i = 0; i < DVD.allDVDsInTheLibrary.size(); i++) {
            if (DVD.allDVDsInTheLibrary.get(i).getIsbnNumber().equals(ISBN)) {
                foundItem = true;
                DVD.allDVDsInTheLibrary.remove(i);
                System.out.println("DVD Deleted!");
                System.out.println("Total number of free spaces left in the library: " + LibraryItem.remainingItemCount);
                System.out.println("Number of free spaces left for books: " + Book.remainingBookCount);
                System.out.println("Number of free spaces left for dvds: : " + DVD.remainingDVDCount);
                break;
            }
        }

        if (!foundItem)
            System.out.println("Item not found! Please enter a valid ISBN number.");

        // displayMainMenu();
    }

    @Override
    public void displayItemsList() {
        if (Book.allBooksInTheLibrary.size() > 0 || DVD.allDVDsInTheLibrary.size() > 0) {
            for (int i = 0; i < Book.allBooksInTheLibrary.size(); i++)
                System.out.println(Book.allBooksInTheLibrary.get(i).getIsbnNumber() + " - Book - " + Book.allBooksInTheLibrary.get(i).getTitle());

            for (int i = 0; i < DVD.allDVDsInTheLibrary.size(); i++)
                System.out.println(DVD.allDVDsInTheLibrary.get(i).getIsbnNumber() + " - DVD - " + DVD.allDVDsInTheLibrary.get(i).getTitle());
        } else
            System.out.println("Currently no items available in the library!");

        //  displayMainMenu();
    }

    public List<Book> showBooks() {
        return Book.getAllBooks();
    }

    @Override
    public void borrowItem(String isbn) {
        System.out.println("Enter ISBN number: ");
        isbn = sc.next();
        boolean isBorrowed = false;
        String itemType = "";
        boolean isIsbnFound = false;

        // handle if the ISBN number is not found
        for (int j = 0; j < allLibraryItems.size(); j++) {
            if (allLibraryItems.get(j).getIsbnNumber().equals(isbn))
                isIsbnFound = true;
        }

        while (!isIsbnFound) {
            System.out.println("xxxxxxxxxxxxx Invalid ISBN number! Please enter a valid isbn number xxxxxxxxxx");
            System.out.println("Enter ISBN number: ");
            isbn = sc.next();
        }

        // Checking if the item has been borrowed by another person
        for (int i = 0; i < borrowedLibraryItems.size(); i++) {
            if (borrowedLibraryItems.get(i).getIsbnNumber().equals(isbn)) {
                isBorrowed = true;
                System.out.println("Sry! This item has been borrowed by another person and will be available in "
                        + borrowedLibraryItems.get(i).getDueDate());
                break;
            }
        }

        if (!isBorrowed) {
            // Getting the item type(Book/ DVD)
            for (int i = 0; i < allLibraryItems.size(); i++) {
                if (allLibraryItems.get(i).getIsbnNumber().equals(isbn)) {
                    itemType = allLibraryItems.get(i).getType();

                    System.out.println("Enter name of the reader: ");
                    String name = sc.next();

                    if (itemType.equals("Book")) {
                        Book book = new Book(itemType, isbn, name, new Date());
                        borrowedLibraryItems.add(book);
                        System.out.println("Due date: " + book.getDueDate());
                    } else if (itemType.equals("DVD")) {
//                        DVD dvd = new DVD(itemType, isbn, name, new Date());
//                        borrowedLibraryItems.add(dvd);
//                        System.out.println(("Due date: " + dvd.getDueDate()));
                    }

                    // Change the availability status of the item in the library
                    allLibraryItems.get(i).setIsAvailableToBorrow(false);
                    break;
                }
            }
        }
    }

    @Override
    public void returnItem() {
        String dueDate = "";
        String returnedTime = "";
        System.out.println("Enter ISBN number: ");
        String isbn = sc.next();
        int numberOfHours = 0;
        String itemType = "";

        Book book = new Book(isbn, 0, 0); // Temporary book object

//        String isbnNumber,
// String title, String publicationDate, String producer,
// ArrayList<String> availableLanguages, ArrayList<String>availableSubtitles,
// ArrayList<String> actors) {

        DVD dvd = new DVD(false,isbn, "title", "producer", availableLanguages,availableSubtitles,actors ); // Temporary DVD object

        for (int i = 0; i < borrowedLibraryItems.size(); i++) {
            // get the due date of the borrowed item
            if (borrowedLibraryItems.get(i).getIsbnNumber().equals(isbn)) {
                dueDate = borrowedLibraryItems.get(i).getDueDate();
                itemType = borrowedLibraryItems.get(i).getType();
                break;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        Date dateReturned = new Date();
        returnedTime = simpleDateFormat.format(dateReturned).substring(11, 21);
        System.out.println("Returned date: " + simpleDateFormat.format(dateReturned));

        // Checking if the item is returned after the period allowed
        if (simpleDateFormat.format(dateReturned).substring(0, 10).compareTo(dueDate.substring(0, 10)) == 0
                || simpleDateFormat.format(dateReturned).substring(0, 10).compareTo(dueDate.substring(0, 10)) < 0) {
            // dateReturned <= dueDate
            // 06/12/2018 08/12/2018
            System.out.println("Compare to: "
                    + simpleDateFormat.format(dateReturned).substring(0, 10).compareTo(dueDate.substring(0, 10)));
            System.out.println("Borrowed item has been returned successfully!");
        } else {
            // If the item has been returned after the period allowed, then calculate the
            // fee
            Iterator<String> a = (Iterator<String>) LibraryItem.dueDates.entrySet();
            while (a.hasNext()) {
                System.out.println(a.next());
            }

            if (returnedTime.substring(20, 22).equals("PM")) {

                switch (returnedTime.substring(11, 13)) {
                    case "01":
                        returnedTime = "13" + returnedTime.substring(14, 19);
                        break;
                    case "02":
                        returnedTime = "14" + returnedTime.substring(14, 19);
                        break;
                    case "03":
                        returnedTime = "15" + returnedTime.substring(14, 19);
                        break;
                    case "04":
                        returnedTime = "16" + returnedTime.substring(14, 19);
                        break;
                    case "05":
                        returnedTime = "17" + returnedTime.substring(14, 19);
                        break;
                    case "06":
                        returnedTime = "18" + returnedTime.substring(14, 19);
                        break;
                    case "07":
                        returnedTime = "19" + returnedTime.substring(14, 19);
                        break;
                    case "08":
                        returnedTime = "20" + returnedTime.substring(14, 19);
                        break;
                    case "09":
                        returnedTime = "21" + returnedTime.substring(14, 19);
                        break;
                    case "10":
                        returnedTime = "22" + returnedTime.substring(14, 19);
                        break;
                    case "11":
                        returnedTime = "23" + returnedTime.substring(14, 19);
                        break;
                    case "12":
                        returnedTime = "24" + returnedTime.substring(14, 19);
                        break;

                }

                // Get the time difference between the returned time & the due time in order to
                // calculate the fee


            }

            if (numberOfHours <= 24 * 3) {
                // The reader will have to pay £0.2 any extra hour for the first 3 days
                if (itemType.equals("Book")) {
                    book.setFee(numberOfHours * 0.2);
                    book.setBorrowedPeriod(numberOfHours);
                    // Add the item to the overdueItemsList along with the corresponding fee &
                    // borrowed period
                    itemsOverdue.add(book);
                } else if (itemType.equals("DVD")) {
                    dvd.setFee(numberOfHours * 0.2);
                    dvd.setBorrowedPeriod(numberOfHours);
                    itemsOverdue.add(dvd);
                }

                System.out.println("You have to pay £" + numberOfHours * 0.2 + " as the fine!");

            } else if (numberOfHours > 24 * 3) {
                // The reader will have to pay £0.2 any extra hour for the first 3 days, then
                // £0.5 per hour.
                if (itemType.equals("Book")) {
                    book.setFee((24 * 3) * 0.2 + (numberOfHours - (24 * 3)) * 0.5);
                    book.setBorrowedPeriod(numberOfHours);
                    itemsOverdue.add(book);
                } else if (itemType.equals("dvd")) {
                    dvd.setFee((24 * 3) * 0.2 + (numberOfHours - (24 * 3)) * 0.5);
                    dvd.setBorrowedPeriod(numberOfHours);
                    itemsOverdue.add(dvd);
                }

                System.out.println(
                        "You have to pay £" + (24 * 3) * 0.2 + (numberOfHours - (24 * 3)) * 0.5 + " as the fine!");
            }
        }

        // Change the availability status of the particular item in the library after
        // returning it
        for (int i = 0; i < allLibraryItems.size(); i++) {
            if (allLibraryItems.get(i).getIsbnNumber().equals(isbn)) {
                allLibraryItems.get(i).setIsAvailableToBorrow(true);
                break;
            }
        }
    }

    @Override
    public void generateReport() {

    }

    @Override
    public void makeReservation() {
        System.out.println("Enter ISBN number: ");
        String isbn = sc.next();
        System.out.println("Enter ID of the reader: ");
        String id = sc.next();
        ArrayList<String> reservations = new ArrayList<>();

        boolean isIsbnFound = false;

        // handle if the ISBN number is not found
        for (int j = 0; j < allLibraryItems.size(); j++) {
            if (allLibraryItems.get(j).getIsbnNumber().equals(isbn))
                isIsbnFound = true;
        }

        while (!isIsbnFound) {
            System.out.println("xxxxxxxxxxxxx Invalid ISBN number! Please enter a valid isbn number xxxxxxxxxx");
            System.out.println("Enter ISBN number: ");
            isbn = sc.next();
        }

        // reservations.add(arg0)

        // calculate an estimate of when the book will be available and display it to
        // the user.

        // also more than one user can make a reservation and so the time to wait for
        // the item will be summed up!

    }
}
