package com.clas;

public class DingDan {
	private int fabu_id;
	private String fabu_user;
	private String fabu_title;
	private String fabu_content;
	private String fabu_money;
	private String fabu_time;
	private String fabu_status;
	private String fabu_userjiedan;
	
	public String getFabu_userjiedan() {
		return fabu_userjiedan;
	}
	public void setFabu_userjiedan(String fabu_userjiedan) {
		this.fabu_userjiedan = fabu_userjiedan;
	}
	public DingDan(){
		super();
	}
	public DingDan(int fabu_id,String fabu_user,String fabu_status,String fabu_content,String fabu_title,String fabu_money,String fabu_time,String fabu_userjiedan){
		super();
		this.fabu_id = fabu_id;
		this.fabu_status = fabu_status;
		this.fabu_user = fabu_user;
		this.fabu_title = fabu_title;
		this.fabu_content = fabu_content;
		this.fabu_money = fabu_money;
		this.fabu_time = fabu_time;
		this.fabu_userjiedan = fabu_userjiedan;
		
	}
	public DingDan(String fabu_status){
		super();
		this.fabu_status = fabu_status;
	}
	public DingDan(int fabu_id,String fabu_user,String fabu_status,String fabu_content,String fabu_title,String fabu_money,String fabu_time){
		super();
		this.fabu_id = fabu_id;
		this.fabu_status = fabu_status;
		this.fabu_user = fabu_user;
		this.fabu_title = fabu_title;
		this.fabu_content = fabu_content;
		this.fabu_money = fabu_money;
		this.fabu_time = fabu_time;
		
	}
	public int getFabu_id() {
		return fabu_id;
	}
	public void setFabu_id(int fabu_id) {
		this.fabu_id = fabu_id;
	}
	public String getFabu_status() {
		return fabu_status;
	}
	public void setFabu_status(String fabu_status) {
		this.fabu_status = fabu_status;
	}
	public String getFabu_user() {
		return fabu_user;
	}
	public void setFabu_user(String fabu_user) {
		this.fabu_user = fabu_user;
	}
	public String getFabu_title() {
		return fabu_title;
	}
	public void setFabu_title(String fabu_title) {
		this.fabu_title = fabu_title;
	}
	public String getFabu_content() {
		return fabu_content;
	}
	public void setFabu_content(String fabu_content) {
		this.fabu_content = fabu_content;
	}
	public String getFabu_money() {
		return fabu_money;
	}
	public void setFabu_money(String fabu_money) {
		this.fabu_money = fabu_money;
	}
	public String getFabu_time() {
		return fabu_time;
	}
	public void setFabu_time(String fabu_time) {
		this.fabu_time = fabu_time;
	}
	

}
