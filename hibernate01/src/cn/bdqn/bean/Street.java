package cn.bdqn.bean;

/*
 * 街道实体类
 */
public class Street{
	private Integer id; 
	private String name; //街道名称
	private District district; //街道所属区县

	
	
	@Override
	public String toString() {
		return "Street [id=" + id + ", name=" + name + ", district=" + district
				+ "]";
	}

	public Street() {
	}

	public Street(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Street(Integer id, District district, String name) {
		this.id = id;
		this.district = district;
		this.name = name;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}