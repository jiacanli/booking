package com.alpha.booking.model;

import java.util.Date;

public class Orders {
    private String orderNum;

    private Date createTime;

    private Integer orderStatus;

    private Double total;

    private String comments;

    private String joinDiscount;

    private Long restaurantId;

    private Integer tableNum;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer status) {
        this.orderStatus = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comment) {
        this.comments = comment == null ? null : comment.trim();
    }

    public String getJoinDiscount() {
        return joinDiscount;
    }

    public void setJoinDiscount(String joinDiscount) {
        this.joinDiscount = joinDiscount == null ? null : joinDiscount.trim();
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }
}