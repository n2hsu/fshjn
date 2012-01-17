package com.wgc.beans;

import java.sql.Timestamp;

public class Student implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4623831599052460454L;
	private String xh;
	private String xm;
	private String xb;
	private String bjm;
	private Timestamp csny;
	private String lxfs;
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getBjm() {
		return bjm;
	}
	public void setBjm(String bjm) {
		this.bjm = bjm;
	}
	public Timestamp getCsny() {
		return csny;
	}
	public void setCsny(Timestamp csny) {
		this.csny = csny;
	}
	public String getLxfs() {
		return lxfs;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

}
