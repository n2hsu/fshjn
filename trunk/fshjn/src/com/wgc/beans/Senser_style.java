package com.wgc.beans;

public class Senser_style implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4102333295244652896L;
	private int cgqlxID;
	private String cgqlxm;
	private Integer mini;
	private Integer maxi;
	private Integer dxz;
	private String mnl;
	
	public int getCgqlxID() {
		return cgqlxID;
	}
	public void setCgqlxID(int cgqlxID) {
		this.cgqlxID = cgqlxID;
	}
	public String getCgqlxm() {
		return cgqlxm;
	}
	public void setCgqlxm(String cgqlxm) {
		this.cgqlxm = cgqlxm;
	}
	public Integer getMini() {
		return mini;
	}
	public void setMini(Integer mini) {
		this.mini = mini;
	}
	public Integer getMaxi() {
		return maxi;
	}
	public void setMaxi(Integer maxi) {
		this.maxi = maxi;
	}
	public Integer getDxz() {
		return dxz;
	}
	public void setDxz(Integer dxz) {
		this.dxz = dxz;
	}
	public String getMnl() {
		return mnl;
	}
	public void setMnl(String mnl) {
		this.mnl = mnl;
	}
}
