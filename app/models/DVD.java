package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DVD extends LibraryItem {
    private String producer;
    ArrayList<String> availableLanguages;
    ArrayList<String> availableSubtitles;
    ArrayList<String> actors;
    static ArrayList<DVD> allDVDsInTheLibrary = new ArrayList<>();
    public static final int MAX_DVD_COUNT = 50;
    public static int currentDvdCount = 0;
    public static int remainingDVDCount = MAX_DVD_COUNT - currentDvdCount;
    private boolean availability = true;

    public DVD(boolean availability, String isbnNumber, String title,String publicationDate,  ArrayList<String> availableLanguages, ArrayList<String> availableSubtitles, ArrayList<String> actors) {
        super(availability, isbnNumber, title, publicationDate);

        this.availableLanguages = availableLanguages;
        this.availableSubtitles = availableSubtitles;
        this.actors = actors;

    }

    public DVD( String isbnNumber, String title,String publicationDate,  ArrayList<String> availableLanguages, ArrayList<String> availableSubtitles, ArrayList<String> actors) {
        super( isbnNumber, title, publicationDate);

        this.availableLanguages = availableLanguages;
        this.availableSubtitles = availableSubtitles;
        this.actors = actors;

    }

    public DVD(String isbnNumber, String title, String publicationDate, String producer, ArrayList<String> availableLanguages,
               ArrayList<String> availableSubtitles, ArrayList<String> actors){
        super( isbnNumber, title, publicationDate);

        this.availableLanguages = availableLanguages;
        this.availableSubtitles = availableSubtitles;
        this.actors = actors;
    }

    public DVD(boolean availability, String isbnNumber, String title, String publicationDate, String producer, ArrayList<String> availableLanguages, ArrayList<String> availableSubtitles, ArrayList<String> actors) {
        super(availability, isbnNumber, title, publicationDate);
        this.producer = producer;
        this.availableLanguages = availableLanguages;
        this.availableSubtitles = availableSubtitles;
        this.actors = actors;
    }

    public DVD() {

    }
    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public ArrayList<String> getAvailableLanguages() {
        return availableLanguages;
    }

    public void setAvailableLanguages(ArrayList<String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public ArrayList<String> getAvailableSubtitles() {
        return availableSubtitles;
    }

    public void setAvailableSubtitles(ArrayList<String> availableSubtitles) {
        this.availableSubtitles = availableSubtitles;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public static ArrayList<DVD> getAllDVDsInTheLibrary() {
        return allDVDsInTheLibrary;
    }

    @Override
    public String getDueDate() {
        Date date = new Date();
        //due date will be in 7 days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 3);

        DateFormat inputFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
        try {
            date = inputFormat.parse(String.valueOf(cal.getTime()));
        } catch (ParseException e) {
            e.getMessage();
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
