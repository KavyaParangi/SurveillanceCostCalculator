package com.provigil.calculator.pojo;

public class Subscriptions {
	
	private int id;
	private int area;
	private String plan;
	private String location;
	
	public Subscriptions() {
		super();
	}

	public Subscriptions(int id, int area, String plan, String location) {
		super();
		this.id = id;
		this.area = area;
		this.plan = plan;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Subscriptions [id=" + id + ", area=" + area + ", plan=" + plan + ", location=" + location + "]";
	}
	
	
	
}
