package com.wgc.beans;

import com.wgc.beans.Senser_style;

public class Senser implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4652950858745953018L;
	private String cgqID;
	private String cgqmc;
	private Senser_style ss;
	private Short sfsh;
	
	public Short getSfsh() {
		return sfsh;
	}
	public void setSfsh(Short sfsh) {
		this.sfsh = sfsh;
	}
	public String getCgqID() {
		return cgqID;
	}
	public void setCgqID(String cgqID) {
		this.cgqID = cgqID;
	}
	public String getCgqmc() {
		return cgqmc;
	}
	public void setCgqmc(String cgqmc) {
		this.cgqmc = cgqmc;
	}
	public Senser_style getSs() {
		return ss;
	}
	public void setSs(Senser_style ss) {
		this.ss = ss;
	}
}
