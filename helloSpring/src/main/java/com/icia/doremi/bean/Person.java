package com.icia.doremi.bean;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
//@Component("person")
@Component
@Setter @Getter //자동 get,set만들어줌
@Accessors(chain = true) //체이닝
@Data //get,set 생성자 등 여러 작업 할 수 있는 것을 한번에 함
public class Person {
	private String id;
	private String pw;
	private int age;
	
	public String sleep() {
		System.out.println("people sleep");
		return "people";
	}
}
