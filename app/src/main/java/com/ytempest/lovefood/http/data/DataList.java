package com.ytempest.lovefood.http.data;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class DataList<Data> {

    private long total;
    private int pageSize;
    private int currentPage;
    private int pageCount;
    private List<Data> list;

    public DataList() {

    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "DataList{" +
                "total=" + total +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", list=" + list +
                '}';
    }
}
