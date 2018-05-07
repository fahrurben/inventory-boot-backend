package com.kyrosoft.inventory.model.dto;

/**
 * Base DTO, base class for data tranfer object.
 * Using in search function
 *
 * @author fahrur
 * @version 1.0
 */
public class BaseDTO {

    /**
     * The page
     */
    private Integer page;

    /**
     * The size post per page
     */
    private Integer size;

    /**
     * Sort by
     */
    private String sortBy;

    /**
     * Sort type
     */
    private SortType sortType;

    /**
     * Constructor
     */
    protected BaseDTO() {}

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
