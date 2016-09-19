package server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    private int id;
    private String name;
    private String genre;
    private int year;
    private String rating;

    public Movie() {
        // Jackson deserialization
    }
    
    public Movie(int inId, String inName, String inGenre, int inYear, String inRating) {
        this.id = inId;
        this.name = inName;
        this.genre = inGenre;
        this.year = inYear;
        this.rating = inRating;
    }    
    
    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty    
    public String getGenre() {
        return genre;
    }

    @JsonProperty
    public int getYear() {
        return year;
    }

    @JsonProperty
    public String getRating() {
        return rating;
    }
    
    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty    
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty    
    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty    
    public void setRating(String rating) {
        this.rating = rating;
    }    

}