package com.example.myapplication;

public class Movie {

    private Integer id;
    private String title;
    private String year;
    private String poster;
    private String overview;
    private Double rating;

    public Movie(String title , String year, String poster , String overview , Double rating){
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.overview = overview;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public Double getRating() {
        return rating;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
