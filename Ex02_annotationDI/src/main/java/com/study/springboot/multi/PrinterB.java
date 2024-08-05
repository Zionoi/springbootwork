package com.study.springboot.multi;

import org.springframework.stereotype.Component;

@Component  //객체이름을 안넣으면 앞글자만 소문자로 들어감 printerB 이렇게 들어가게됨.
public class PrinterB implements Printer {

	@Override
	public void print(String msg) {
		System.out.println("PrinterB : " + msg);

	}

}
