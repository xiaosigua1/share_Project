package cn.bdqn.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 区县实体类
 */
public class District {
	private Integer id;
	private String name;
	private List<Street> streets = new ArrayList<Street>();

	
	
	@Override
	public String toString() {
		return "District [id=" + id + ", name=" + name + "]";
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public District() {
	}

	public Integer getId() {
		return id;
	}

	public District(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}