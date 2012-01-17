package com.wgc.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Building implements Serializable {

	 private Short lh;
     private String lm;
     private String cwzs;
     private Short yycws;
     private String jss;

    public Short getLh() {
        return this.lh;
    }
    
    public void setLh(Short lh) {
        this.lh = lh;
    }

    public String getLm() {
        return this.lm;
    }
    
    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getCwzs() {
        return this.cwzs;
    }
    
    public void setCwzs(String cwzs) {
        this.cwzs = cwzs;
    }

    public Short getYycws() {
        return this.yycws;
    }
    
    public void setYycws(Short yycws) {
        this.yycws = yycws;
    }

    public String getJss() {
        return this.jss;
    }
    
    public void setJss(String jss) {
        this.jss = jss;
    }
   








}