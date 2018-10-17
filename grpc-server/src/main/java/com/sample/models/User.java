package com.sample.models;

public class User {
	
	private String fname;
	private String lname;
	
	public User() {}

	public User(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + "]";
	}
}
