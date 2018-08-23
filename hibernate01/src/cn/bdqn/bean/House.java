package cn.bdqn.bean;

import java.util.Date;


public class House{


	private Integer id;
	private String title;//标题
	private String description;//描述
	private Double price;//价格
	private Date pubdate;//发布时间
	private Double floorage;//面积
	private String contact;//联系人


	public House() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public Double getFloorage() {
		return floorage;
	}

	public void setFloorage(Double floorage) {
		this.floorage = floorage;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}