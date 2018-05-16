package com.alpha.booking.model;

public class OrderItem {
    private Integer id;

    private String orderNum;

    private Long itemId; // 菜品id

    private Integer amount; // 点菜数量

    private Double price; // 总价
    
    
    
    public OrderItem(String order_num,Long item_id,int amount,double price) {
    	this.amount = amount;
    	this.orderNum  = order_num;
    	this.price = price;
    	this.itemId = item_id;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}