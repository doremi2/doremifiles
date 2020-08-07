package com.icia.doremi.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

//@Component("student")
//@Component
//IOC(DI)컨테이너에 객체 생성 인스턴스 생성 싱글톤 생성
@Data
public class Student extends Person {
	private String name;
	private int sNo;
	
	
	public void study() {
		System.out.println("study");
	}
	public String sleep() {
		System.out.println("sleep");
		return "sleep";
	}
}
