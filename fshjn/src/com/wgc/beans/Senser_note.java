package com.wgc.beans;

import java.sql.Timestamp;

public class Senser_note implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819627070259411627L;
	private Long cgqjlID;
	private Senser senser;
	private String cgqz;
	private Timestamp jlsj;
	
	public Long getCgqjlID() {
		return cgqjlID;
	}
	public void setCgqjlID(Long cgqjlID) {
		this.cgqjlID = cgqjlID;
	}
	public Senser getSenser() {
		return senser;
	}
	public void setSenser(Senser senser) {
		this.senser = senser;
	}
	public String getCgqz() {
		return cgqz;
	}
	public void setCgqz(String cgqz) {
		this.cgqz = cgqz;
	}
	public Timestamp getJlsj() {
		return jlsj;
	}
	public void setJlsj(Timestamp jlsj) {
		this.jlsj = jlsj;
	}
	
}
