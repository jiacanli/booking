package com.alpha.booking.model;

import java.util.Date;

public class Discount {
    private Integer id;

    private String description;

    private Long restaurantId;

    private Date createDate;

    private Date beginDate;

    private Date endDate;

    private Integer type;

    private Integer orderChange;

    private String items;

    private String againstDiscount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderChange() {
        return orderChange;
    }

    public void setOrderChange(Integer orderChange) {
        this.orderChange = orderChange;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }

    public String getAgainstDiscount() {
        return againstDiscount;
    }

    public void setAgainstDiscount(String againstDiscount) {
        this.againstDiscount = againstDiscount == null ? null : againstDiscount.trim();
    }
}