package com.ams.entity;

public class ClubMembers {
	private int cmid;
	private int uid;
	private String sduid;
	private String name;
	private String major;
	private String class_num;
	private String user_logo;
	private int cid;
	private String cname;
	private String club_contents;
	private String club_logo;
	private int president_flag;
	private String mem_state;
	private String reason;
	public int getCmid() {
		return cmid;
	}
	public void setCmid(int cmid) {
		this.cmid = cmid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPresident_flag() {
		return president_flag;
	}
	public void setPresident_flag(int president_flag) {
		this.president_flag = president_flag;
	}
	public String getMem_state() {
		return mem_state;
	}
	public void setMem_state(String mem_state) {
		this.mem_state = mem_state;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSduid() {
		return sduid;
	}
	public void setSduid(String sduid) {
		this.sduid = sduid;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClass_num() {
		return class_num;
	}
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getUser_logo() {
		return user_logo;
	}
	public void setUser_logo(String user_logo) {
		this.user_logo = user_logo;
	}
	public String getClub_contents() {
		return club_contents;
	}
	public void setClub_contents(String club_contents) {
		this.club_contents = club_contents;
	}
	public String getClub_logo() {
		return club_logo;
	}
	public void setClub_logo(String club_logo) {
		this.club_logo = club_logo;
	}
	

}
