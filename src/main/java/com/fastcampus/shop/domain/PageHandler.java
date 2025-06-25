package com.fastcampus.shop.domain;

import lombok.Getter;

@Getter
public class PageHandler {
    private int totalCount;
    private int pageSize;
    private int naviSize=5;
    private int totalPage;
    private int currentPage;
    private int beginPage;
    private int endPage;
    private boolean showPrevious;
    private boolean showNext;

    public PageHandler(int totalCount, int page){
        this(totalCount, page, 6);
    }

    public PageHandler(int totalCount, int page, int pageSize) {
        this.totalCount = totalCount;
        this.currentPage = page;
        this.pageSize = pageSize;

        totalPage = (int)Math.ceil((double)totalCount / pageSize);
        beginPage = (page-1)/naviSize * naviSize +1;
        endPage = Math.min((beginPage + naviSize)-1, totalPage);
        showPrevious = beginPage != 1;
        showNext = endPage != totalPage;
    }

    void print() {
        System.out.println("page = " + currentPage);
        System.out.print(showPrevious ? "[PREV] " : " ");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? "[NEXT]" : " ");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrevious=" + showPrevious +
                ", showNext=" + showNext +
                '}';
    }
}
