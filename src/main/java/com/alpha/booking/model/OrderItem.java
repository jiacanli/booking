package com.alpha.booking.model;

public class OrderItem {
    private Integer id;

    private String orderNum;

    private Long itemId; // 菜品id

    private Integer amount; // 点菜数量

    private Double price; // 总价
    
    public OrderItem() {}
        
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
    /*
     * 
     * @ClassName: OrderItemDetail 
     * @Description: TODO
     * @author: jiacanli
     * @date: 2018年6月21日 下午5:00:58
     * 
     * 订单item页详情展示类
     * id-名称 -- 单价x数量 -- 价钱
     */
    public static class OrderItemDetail{
    	
    	long id;
    	String name;
    	double orig_price;
    	int amount;
    	double price;
    	
    	public OrderItemDetail() {
    		
    	}
    	
    	public OrderItemDetail(long id,String name,int amount,double orig_price,double price) {
    		this.id = id;
    		this.name = name;
    		this.orig_price = orig_price;
    		this.amount = amount;
    		this.price = price;
    	}
    	
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getOrig_price() {
			return orig_price;
		}
		public void setOrig_price(double orig_price) {
			this.orig_price = orig_price;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
    	
    	
    	
    	
    }
}