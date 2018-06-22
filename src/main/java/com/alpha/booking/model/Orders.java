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

    private String tableNum;
    
    private String items;
    
    
    
    public SimpleFormatOrder transfer() {
    	return new SimpleFormatOrder(this.orderNum, this.tableNum,this,orderNum,createTime);
    }
    
    public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

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

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }
    
    
    /*
     * 
     * 
     * 
     * @ClassName: SimpleFormatOrder 
     * @Description: 返回数据格式
     * @author: jiacanli
     * @date: 2018年6月21日 下午2:26:19
     */
    public class SimpleFormatOrder{
    	String guid;
    	String name;
    	String detail;
    	Date time;
    	Orders others;
    	
    	public SimpleFormatOrder(String name,String detail,Orders others,String guid,Date time) {
    		this.name = name;
    		this.detail = detail;
    		this.others = others;
    		this.guid = guid;
    		this.time = time;
    	}
    	
		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public Orders getOthers() {
			return others;
		}
		public void setOthers(Orders others) {
			this.others = others;
		}
    	
    	
    }
}