package cn.bdqn.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Emp {
	 private Integer empNo;  //
	 private String empName; //
	 private String job; //职位
	 private Dept dept;  //员工所属的部门


	

	@Override
	public String toString() {
		return "Emp [empNo=" + empNo + ", empName=" + empName + ", job=" + job
				+ ", dept=" + dept + "]";
	}




	public Dept getDept() {
		return dept;
	}




	public void setDept(Dept dept) {
		this.dept = dept;
	}




	public Emp(Integer empNo, String empName, String job) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
	}




	public Integer getEmpNo() {
		return empNo;
	}




	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}




	public String getEmpName() {
		return empName;
	}




	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public String getJob() {
		return job;
	}




	public void setJob(String job) {
		this.job = job;
	}




	public Emp() {
		super();
	}

	 
}
