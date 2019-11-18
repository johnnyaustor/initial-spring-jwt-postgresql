/* by JAPBuilder */
package com.jap.initial.springjwt.payload;

import org.springframework.data.domain.Page;

public class MetaInfoResponse {
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int size;
    private int number;

    public MetaInfoResponse() {
    }

    public MetaInfoResponse(Page page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.numberOfElements = page.getNumberOfElements();
        this.size = page.getSize();
        this.number = page.getNumber();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public int getNumber() {
        return number;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
