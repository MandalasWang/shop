package com.wang.domain;

public class Administrantor {
	private Integer uid;
	private String username;
	private String password;
	private String name;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Administrantor [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name
				+ "]";
	}
	
}
