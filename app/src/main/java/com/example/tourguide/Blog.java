package com.example.tourguide;

class Blog {
    private String id;
    private String title;
    private String description;
    private String date;
    private String image;
    private Author author;
    private int views;
    private float rating;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getViews() {
        return views;
    }

    public float getRating() {
        return rating;
    }
}
