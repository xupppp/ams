package com.ams.entity;

public class Notice {
	private int nid;
	private int cid;
	private String noti_contents;
	private String noti_state;
	private String noti_time;
	
	private String cname;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getNoti_contents() {
		return noti_contents;
	}
	public void setNoti_contents(String noti_contents) {
		this.noti_contents = noti_contents;
	}
	public String getNoti_state() {
		return noti_state;
	}
	public void setNoti_state(String noti_state) {
		this.noti_state = noti_state;
	}
	public String getNoti_time() {
		return noti_time;
	}
	public void setNoti_time(String noti_time) {
		this.noti_time = noti_time;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	

}
