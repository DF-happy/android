package com.dyt.bean;
/*
 * ������ʱ�洢����
 */
public class Book {
	private String title;//����
	private String content;//����
	private String times;//ʱ��
	private int ids;//���
	public Book(String ti,int id,String con ,String time){
		this.ids=id;
		this.title=ti;
		this.content=con;
		this.times=time;
	}
	public Book(String ti,String con,String time){
		this.title=ti;
		this.content=con;
		this.times=time;
	}
	public Book(int i,String ti,String time){
		this.ids=i;
		this.title=ti;
		this.times=time;
	}
	public Book(String ti,String con){
		this.title=ti;
		this.content=con;
	}
	public int getIds() {
		return ids;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getTimes() {
		return times;
	}

}
