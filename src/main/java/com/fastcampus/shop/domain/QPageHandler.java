package com.fastcampus.shop.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class QPageHandler {

    /*private Integer currentPage;
        private Integer pageSize;
        private String keyword;
        private String option;
    */
    private QSearchCondition sc;

    private int totalCnt; //총 게시물 개수
    private int naviSize = 10; // 페이지 내비게이션의 크기
    private int totalPage;
    private int startPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public QPageHandler(int totalCnt, QSearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public void doPaging(int totalCnt, QSearchCondition sc) {
        this.totalCnt = totalCnt;

        totalPage = (int) Math.ceil((double) totalCnt / sc.getPageSize());
        startPage = (sc.getCurrentPage() - 1) / naviSize * naviSize + 1;
        endPage = Math.min(startPage + naviSize - 1, totalPage);
        showPrev = startPage != 1;
        showNext = endPage != totalPage;
    }

    public QSearchCondition getSc() {
        return sc;
    }

    public void setSc(QSearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
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
        System.out.println("page : " + sc.getCurrentPage() );
        System.out.print(showPrev? "[prev]" : " " );
        for( int i = startPage; i <= endPage; i++ ) {
            System.out.printf( "%3d", i );
        }
        System.out.println(showNext? "[next]" : " ");
    }

    @Override
    public String toString() {
        return "QPageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}