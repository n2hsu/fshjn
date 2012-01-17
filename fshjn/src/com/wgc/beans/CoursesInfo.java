package com.wgc.beans;

public class CoursesInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6710886715688850615L;
	private Long kcID;
	private Course course;
	private Short js;
	private String xq;
	private String bjm;
	private Teacher teacher;
	private String jsh;
	
	public Long getKcID() {
		return kcID;
	}
	public void setKcID(Long kcID) {
		this.kcID = kcID;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Short getJs() {
		return js;
	}
	public void setJs(Short js) {
		this.js = js;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getBjm() {
		return bjm;
	}
	public void setBjm(String bjm) {
		this.bjm = bjm;
	}
	public String getJsh() {
		return jsh;
	}
	public void setJsh(String jsh) {
		this.jsh = jsh;
	}
	
}
