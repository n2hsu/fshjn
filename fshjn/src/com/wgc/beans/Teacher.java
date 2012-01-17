package com.wgc.beans;

import java.sql.Timestamp;

public class Teacher implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -425107154175637741L;
	private String jgh;
	private String xm;
	private String xb;
	private String xy;
	private Timestamp csny;
	private String lxfs;
	
	public String getJgh() {
		return jgh;
	}
	public void setJgh(String jgh) {
		this.jgh = jgh;
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
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
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
