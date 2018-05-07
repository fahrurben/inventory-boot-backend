package com.kyrosoft.inventory.model;

import java.util.List;

/**
 * Search result
 *
 * @param <T> the type parameter
 */
public class SearchResult<T> {

    /**
     * Total data
     */
    private long total;

    /**
     * Total page
     */
    private int totalPage;

    /**
     * Data
     */
    private List<T> data;

    /**
     * Constructor
     */
    public SearchResult() {}

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
