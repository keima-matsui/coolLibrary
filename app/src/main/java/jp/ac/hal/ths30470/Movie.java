package jp.ac.hal.ths30470;

import java.io.Serializable;
import java.text.DateFormat;

/**
 * Created by keima on 15/07/07.
 */
public class Movie implements Serializable {
    int id;
    String title;
    String genre;
    String actor;
    String rating;
    String date;

    public Movie(int id, String title, String genre, String actor, String rating,String date) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.actor = actor;
        this.rating = rating;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        
        return getTitle();
    }


}
