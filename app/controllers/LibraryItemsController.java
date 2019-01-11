package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;

public class LibraryItemsController extends Controller {

    WestminsterLibraryManager libraryManager = new WestminsterLibraryManager();

    public Result returnBook() {

        Book bo = null;
        JsonNode json = request().body().asJson();
        System.out.println("JSON is");
        System.out.println(json);
        String isbn = json.get("isbn").toString();
        System.out.println(isbn);

        bo = getBook(isbn);

        if (bo != null) {
            bo.setAvailability(true);
            return ok(new ObjectMapper().convertValue(bo, JsonNode.class));
        }
        return notFound();
    }

    public Result returnDvd() {

        DVD dvd = null;
        JsonNode json = request().body().asJson();
        String isbn = json.get("isbn").toString();
        System.out.println(isbn);

        dvd = getDvd(isbn);

        if (dvd != null) {
            dvd.setAvailability(true);
            return ok(new ObjectMapper().convertValue(dvd, JsonNode.class));
        }
        return notFound();
    }

    public Result getDvds() {
        JsonNode json = new ObjectMapper().convertValue(DVD.getAllDVDsInTheLibrary(), JsonNode.class);
        return ok(json);
    }

    public Result getBooks() {
        JsonNode json = new ObjectMapper().convertValue(Book.getAllBooks(), JsonNode.class);
        return ok(json);
    }

    public Result addBook() {
        JsonNode json = request().body().asJson();
        Book.getAllBooks().add(getBookFromJson(json));
        return ok(new ObjectMapper().convertValue(Book.getAllBooks(), JsonNode.class));
    }

    public Result addDVD() {

        JsonNode json = request().body().asJson();
        DVD.getAllDVDsInTheLibrary().add(getDVDVsfromjson(json));
        return ok(new ObjectMapper().convertValue(DVD.getAllDVDsInTheLibrary(), JsonNode.class));
    }

    public Result findBook() {
        LibraryItem bo = null;
        JsonNode json = request().body().asJson();
        String isbn = json.get("isbn").toString();
        bo = getBook(isbn);

        if (bo != null) {
            ((Book)bo).setAvailability(false);
            return ok(new ObjectMapper().convertValue(bo, JsonNode.class));
        }

        bo = getDvd(isbn);
        if (bo != null) {
            ((DVD)bo).setAvailability(false);
            return ok(new ObjectMapper().convertValue(bo, JsonNode.class));
        }
        return notFound();
    }

    private Book getBook(String isbn) {
        Book bo = null;
        for (Book book : Book.getAllBooks()) {
            if (book.getIsbnNumber().equalsIgnoreCase(isbn)) {
                bo = book;
            }
        }
        return bo;
    }

    private DVD getDvd(String isbn) {
        DVD dvd = null;
        for (DVD dvd1 : DVD.getAllDVDsInTheLibrary()) {
            if (dvd1.getIsbnNumber().equalsIgnoreCase(isbn)) {
                dvd = dvd1;
            }
        }
        return dvd;
    }

    public Result findDVD() {

        DVD dvd = null;
        JsonNode json = request().body().asJson();
        String isbn = json.get("isbn").toString();
        dvd = getDvd(isbn);

        if (dvd != null) {
            dvd.setAvailability(false);
            return ok(new ObjectMapper().convertValue(dvd, JsonNode.class));
        }
        return notFound();
    }

    private static DVD getDVDVsfromjson(JsonNode json) {

        return new DVD(
               // json.get("availability").booleanValue(),
                json.get("isbnNumber").toString(),
                json.get("title").toString(),
                json.get("publicationDate").toString(),
                json.get("producer").toString(), null, null, null
        );
    }


    private Book getBookFromJson(JsonNode json) {

        return new Book(
                // json.get("availability").booleanValue(),
                json.get("isbnNumber").toString(),
                json.get("title").toString(),
                json.get("publicationDate").toString(),
                null,
                json.get("publisher").toString(),
                Integer.parseInt(json.get("totalNumberOfPages").toString()));
    }

    private Reader getReaderFromJson(JsonNode json) {

        return new Reader(

                json.get("id").toString(),
                json.get("firstName").toString(),
                json.get("lastName").toString(),
                json.get("phoneNumber").toString(),
                json.get("email").toString(),null);

    }

    public Result generateReport() {
        return ok();
    }

    public Result addReader(){
            JsonNode json = request().body().asJson();
        System.out.println("Adding reader...");
        System.out.println("------------------------------");
        System.out.println(json.get("id"));
        System.out.println(json.get("firstName"));
        System.out.println(json.get("lastName"));
        System.out.println(json.get("phoneNumber"));
        System.out.println(json.get("email"));
        System.out.println("------------------------------");

            Reader.getAllReaders().add(getReaderFromJson(json));
        System.out.println(Reader.getAllReaders());
            return ok(new ObjectMapper().convertValue(Reader.getAllReaders(), JsonNode.class));
    }
}
