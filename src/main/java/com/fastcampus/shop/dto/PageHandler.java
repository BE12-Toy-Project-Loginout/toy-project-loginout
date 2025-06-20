package com.fastcampus.shop.dto;

public class PageHandler {

    private int totalCnt; //총 게시물 개수
    private int pageSize; // 한 페이지의 크기
    private int naviSize = 10; // 페이지 내비게이션의 크기
    private int totalPage;
    private int currentPage;
    private int startPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt, int currentPage) {
        this(totalCnt, currentPage, 10);
    }

    public PageHandler(int totalCnt, int currentPage, int pageSize) {
        this.totalCnt = totalCnt;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        totalPage = (int) Math.ceil((double) totalCnt / pageSize);
        startPage = (currentPage - 1) / naviSize * naviSize + 1;
        endPage = Math.min(startPage + naviSize - 1, totalPage);
        showPrev = startPage != 1;
        showNext = endPage != totalPage;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    void print() {
        System.out.println("page : " + currentPage );
        System.out.print(showPrev? "[prev]" : " " );
        for( int i = startPage; i <= endPage; i++ ) {
            System.out.printf( "%3d", i );
        }
        System.out.println(showNext? "[next]" : " ");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
