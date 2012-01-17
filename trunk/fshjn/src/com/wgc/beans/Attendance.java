package com.wgc.beans;

import java.sql.Timestamp;

public class Attendance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4373734626157435650L;
	private Long kqID;
	private Course course;
	private Student student;
	private Timestamp skrq;
	private Timestamp jlsj;
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Long getKqID() {
		return kqID;
	}
	public void setKqID(Long kqID) {
		this.kqID = kqID;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Timestamp getSkrq() {
		return skrq;
	}
	public void setSkrq(Timestamp skrq) {
		this.skrq = skrq;
	}
	public Timestamp getJlsj() {
		return jlsj;
	}
	public void setJlsj(Timestamp jlsj) {
		this.jlsj = jlsj;
	}
	
	
}
