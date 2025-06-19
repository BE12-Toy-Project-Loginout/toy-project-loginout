package com.fastcampus.shop.dto;

import java.util.Arrays;
import java.util.Objects;

public class ProductListImageDto {
    private Integer image_id;
    private Integer product_id;
    private String filename;
    private String content_type;
    private byte[] image_data;
    private boolean is_thumbnail;

    public ProductListImageDto() {}
    public ProductListImageDto(Integer image_id, Integer product_id, String filename,
                               String content_type, byte[] image_data, boolean is_thumbnail) {
        this.image_id = image_id;
        this.product_id = product_id;
        this.filename = filename;
        this.content_type = content_type;
        this.image_data = image_data;
        this.is_thumbnail = is_thumbnail;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }

    public boolean isIs_thumbnail() {
        return is_thumbnail;
    }

    public void setIs_thumbnail(boolean is_thumbnail) {
        this.is_thumbnail = is_thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductListImageDto that = (ProductListImageDto) o;
        return Objects.equals(image_id, that.image_id) && Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image_id, product_id);
    }

    @Override
    public String toString() {
        return "ProductImageDto{" +
                "image_id=" + image_id +
                ", product_id=" + product_id +
                ", filename='" + filename + '\'' +
                ", content_type='" + content_type + '\'' +
                ", image_data=" + Arrays.toString(image_data) +
                ", is_thumbnail=" + is_thumbnail +
                '}';
    }
}
