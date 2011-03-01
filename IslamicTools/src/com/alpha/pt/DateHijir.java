package com.alpha.pt;

public class DateHijir{
	
	public DateHijir(Date date,int id_ref) {
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
		this.id_ref = id_ref;
	}
	public DateHijir(int day,int month,int id_ref) {
		this.day = day;
		this.month = month;
		this.id_ref= id_ref;
	}
	
	public DateHijir() {
	}
	
	public int day;
	public int month;
	public int year;
	public int id_ref;
}
