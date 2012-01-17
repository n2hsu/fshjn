package com.wgc.beans;

import java.sql.Timestamp;

public class Car_io implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2903442602013214237L;
	private Long cljcID;
	private String cph;
	private String jczt;
	private String cxm;
	private Timestamp jlsj;
	
	public Long getCljcID() {
		return cljcID;
	}
	public void setCljcID(Long cljcID) {
		this.cljcID = cljcID;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getJczt() {
		return jczt;
	}
	public void setJczt(String jczt) {
		this.jczt = jczt;
	}
	public String getCxm() {
		return cxm;
	}
	public void setCxm(String cxm) {
		this.cxm = cxm;
	}
	public Timestamp getJlsj() {
		return jlsj;
	}
	public void setJlsj(Timestamp jlsj) {
		this.jlsj = jlsj;
	}
}
