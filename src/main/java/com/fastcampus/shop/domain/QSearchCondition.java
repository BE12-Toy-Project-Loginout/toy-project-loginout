package com.fastcampus.shop.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class QSearchCondition {
     private Integer currentPage = 1;
     private Integer pageSize = 10;
    /* private Integer offset = 0;*/
     private String keyword = "";
     private String option = "";

     public QSearchCondition() { }

     public QSearchCondition(Integer currentPage, Integer pageSize, String keyword, String option) {
          this.currentPage = currentPage;
          this.pageSize = pageSize;
          this.keyword = keyword;
          this.option = option;
     }

     public String getQueryString(Integer currentPage) {
          //?page=1&pageSize=10&option=title&keyword=test
          return UriComponentsBuilder.newInstance()
                  .queryParam("currentPage", currentPage)
                  .queryParam("pageSize", pageSize)
                  .queryParam("option", option)
                  .queryParam("keyword", keyword)
                  .build().toString();
     }

     public String getQueryString() {
          //?page=1&pageSize=10&option=title&keyword=test
          return getQueryString(currentPage);
     }

     public Integer getCurrentPage() {
          return currentPage;
     }

     public void setCurrentPage(Integer currentPage) {
          this.currentPage = currentPage;
     }

     public Integer getPageSize() {
          return pageSize;
     }

     public void setPageSize(Integer pageSize) {
          this.pageSize = pageSize;
     }

     public Integer getOffset() {
          return (currentPage - 1) * pageSize;
     }

     public String getKeyword() {
          return keyword;
     }

     public void setKeyword(String keyword) {
          this.keyword = keyword;
     }

     public String getOption() {
          return option;
     }

     public void setOption(String option) {
          this.option = option;
     }

     @Override
     public String toString() {
          return "QSearchCondition{" +
                  "page=" + currentPage +
                  ", pageSize=" + pageSize +
                  ", offset=" + getOffset() +
                  ", keyword='" + keyword + '\'' +
                  ", option='" + option + '\'' +
                  '}';
     }
}