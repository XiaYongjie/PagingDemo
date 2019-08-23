package com.xgxny.bean;

public class DemoRequest {

    /**
     * cat : -1
     * page : 1
     * pageSize : 10
     */

    private int cat;
    private int page;
    private int pageSize;

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
