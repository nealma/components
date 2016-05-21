package com.nealma.framework.model;

import java.util.List;

/**
 * Created by nealpc on 5/18/16.
 */
public class MessageBean {
    private Integer status;
    private String message;
    private List<?> data;
    private Pagination pager;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Pagination getPager() {
        return pager;
    }

    public void setPager(Pagination pager) {
        this.pager = pager;
    }

    public static class Pagination {
        private int currentPage = 1;
        private int pageCount = 1;
        private int pageSize = 0;
        private int totalItem = 0;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalItem() {
            return totalItem;
        }

        public void setTotalItem(int totalItem) {
            this.totalItem = totalItem;
        }

    }
}