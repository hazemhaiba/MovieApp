
package com.garagy.movieapp.data;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids = new ArrayList<Integer>();
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private String release_date;
    private String poster_path;
    private double popularity;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param vote_average
     * @param backdrop_path
     * @param adult
     * @param id
     * @param title
     * @param original_language
     * @param overview
     * @param genre_ids
     * @param original_title
     * @param release_date
     * @param vote_count
     * @param poster_path
     * @param video
     * @param popularity
     */
    public Result(boolean adult, String backdrop_path, List<Integer> genre_ids, int id, String original_language, String original_title, String overview, String release_date, String poster_path, double popularity, String title, boolean video, double vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    /**
     * @return The adult
     */
    public boolean isAdult() {
        return adult;
    }

    /**
     * @param adult The adult
     */
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Result withAdult(boolean adult) {
        this.adult = adult;
        return this;
    }

    /**
     * @return The backdrop_path
     */
    public String getBackdrop_path() {
        return backdrop_path;
    }

    /**
     * @param backdrop_path The backdrop_path
     */
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Result withBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
        return this;
    }

    /**
     * @return The genre_ids
     */
    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    /**
     * @param genre_ids The genre_ids
     */
    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Result withGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
        return this;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    public Result withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return The original_language
     */
    public String getOriginal_language() {
        return original_language;
    }

    /**
     * @param original_language The original_language
     */
    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Result withOriginal_language(String original_language) {
        this.original_language = original_language;
        return this;
    }

    /**
     * @return The original_title
     */
    public String getOriginal_title() {
        return original_title;
    }

    /**
     * @param original_title The original_title
     */
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public Result withOriginal_title(String original_title) {
        this.original_title = original_title;
        return this;
    }

    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Result withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    /**
     * @return The release_date
     */
    public String getRelease_date() {
        return release_date;
    }

    /**
     * @param release_date The release_date
     */
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Result withRelease_date(String release_date) {
        this.release_date = release_date;
        return this;
    }

    /**
     * @return The poster_path
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     * @param poster_path The poster_path
     */
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Result withPoster_path(String poster_path) {
        this.poster_path = poster_path;
        return this;
    }

    /**
     * @return The popularity
     */
    public double getPopularity() {
        return popularity;
    }

    /**
     * @param popularity The popularity
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Result withPopularity(double popularity) {
        this.popularity = popularity;
        return this;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Result withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return The video
     */
    public boolean isVideo() {
        return video;
    }

    /**
     * @param video The video
     */
    public void setVideo(boolean video) {
        this.video = video;
    }

    public Result withVideo(boolean video) {
        this.video = video;
        return this;
    }

    /**
     * @return The vote_average
     */
    public double getVote_average() {
        return vote_average;
    }

    /**
     * @param vote_average The vote_average
     */
    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public Result withVote_average(double vote_average) {
        this.vote_average = vote_average;
        return this;
    }

    /**
     * @return The vote_count
     */
    public int getVote_count() {
        return vote_count;
    }

    /**
     * @param vote_count The vote_count
     */
    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public Result withVote_count(int vote_count) {
        this.vote_count = vote_count;
        return this;
    }

}
