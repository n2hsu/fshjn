package com.wgc.beans;

public class Course implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1165274694892881729L;
	private String kch;
	private String kcm;
	
	public String getKch() {
		return kch;
	}
	public void setKch(String kch) {
		this.kch = kch;
	}
	public String getKcm() {
		return kcm;
	}
	public void setKcm(String kcm) {
		this.kcm = kcm;
	}
}
