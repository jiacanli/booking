package com.alpha.booking.model;

import java.util.Date;

public class Order {
    private String orderNum;

    private Date createTime;

    private Integer status;

    private Double total;

    private String comment;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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