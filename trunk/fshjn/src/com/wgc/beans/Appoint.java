package com.wgc.beans;

import java.sql.Timestamp;

public class Appoint implements java.io.Serializable {

	private static final long serialVersionUID = -7421144851344473501L;
	private int yyID;
	private String yyjs;
	private Student stu;
	private Timestamp yysj;
	private Short js;
	private Boolean shzt;
	private String lxfs;
		
	public String getLxfs() {
		return lxfs;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	public int getYyID() {
		return yyID;
	}
	public void setYyID(int yyID) {
		this.yyID = yyID;
	}
	public String getYyjs() {
		return yyjs;
	}
	public void setYyjs(String yyjs) {
		this.yyjs = yyjs;
	}
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public Timestamp getYysj() {
		return yysj;
	}
	public void setYysj(Timestamp yysj) {
		this.yysj = yysj;
	}
	public Short getJs() {
		return js;
	}
	public void setJs(Short js) {
		this.js = js;
	}
	public Boolean getShzt() {
		return shzt;
	}
	public void setShzt(Boolean shzt) {
		this.shzt = shzt;
	}
	
}
