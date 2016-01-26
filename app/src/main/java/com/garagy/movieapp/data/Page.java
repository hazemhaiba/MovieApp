
package com.garagy.movieapp.data;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private int page;
    private List<Result> results = new ArrayList<Result>();
    private int total_pages;
    private int total_results;

    /**
     * No args constructor for use in serialization
     */
    public Page() {
    }

    /**
     * @param results
     * @param page
     * @param total_pages
     * @param total_results
     */
    public Page(int page, List<Result> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    /**
     * @return The page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(int page) {
        this.page = page;
    }

    public Page withPage(int page) {
        this.page = page;
        return this;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Page withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    /**
     * @return The total_pages
     */
    public int getTotal_pages() {
        return total_pages;
    }

    /**
     * @param total_pages The total_pages
     */
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public Page withTotal_pages(int total_pages) {
        this.total_pages = total_pages;
        return this;
    }

    /**
     * @return The total_results
     */
    public int getTotal_results() {
        return total_results;
    }

    /**
     * @param total_results The total_results
     */
    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public Page withTotal_results(int total_results) {
        this.total_results = total_results;
        return this;
    }

}
