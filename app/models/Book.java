package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Book extends LibraryItem {
    ArrayList<String> authors = new ArrayList<>();
    private String publisher;
    private int totalNumberOfPages = 0;
    static ArrayList<Book> allBooksInTheLibrary = new ArrayList<>();
    public static final int MAX_BOOK_COUNT = 100;
    public static int currentBookCount = 0;
    public static int remainingBookCount = MAX_BOOK_COUNT - currentBookCount;
    private boolean availability = true;

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Book() {

    }

    public Book(String isbnNumber, String title, String publicationDate,
                ArrayList<String> authors, String publisher, int totalNumberOfPages) {
        super(isbnNumber, title, publicationDate);
        this.authors = authors;
        this.publisher = publisher;
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public Book(boolean isAvailableToBorrow, String isbnNumber, String title, String publicationDate,
                ArrayList<String> authors, String publisher, int totalNumberOfPages) {
        super(isAvailableToBorrow, isbnNumber, title, publicationDate);
        this.authors = authors;
        this.publisher = publisher;
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public Book(String type, String isbnNumber, String currentReader, Date dateBorrowed) {
        super.setType(type);
        super.setIsbnNumber(isbnNumber);
        super.setCurrentReader(currentReader);
//		super.setDateBorrowed(dateBorrowed);
		setDueDate();
        DateTime d = new DateTime();
        d.setDate(25, 11, 2018);
        d.setTime(20, 05, 40);

    }

    public Book(String isbnNumber, double fee, int borrowedPeriod) {
        super.setIsbnNumber(isbnNumber);
        super.setFee(fee);
        super.setBorrowedPeriod(borrowedPeriod);
    }


    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooksInTheLibrary;
    }

    @Override
    public String getDueDate() {
        Date date = new Date();
        //due date will be in 7 days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);

        DateFormat inputFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
        try {
            date = inputFormat.parse(String.valueOf(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LibraryItem.dueDates.put(super.getIsbnNumber(), String.valueOf(cal.getTime()));

        System.out.println(String.valueOf(inputFormat.format(date)));
        System.out.println(String.valueOf(outputFormat.format(date)));

        return String.valueOf(date);
    }

    @Override
    public void setDueDate() {
        super.dueDate = getDueDate();
    }
}
