package com.study.springboot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.springboot.multi.Config;
import com.study.springboot.multi.Person;
import com.study.springboot.multi.Printer;

//@SpringBootApplication
public class Ex01JavaDiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Ex01JavaDiApplication.class, args);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		Person Person1 = (Person)context.getBean("Person1");
		Person1.print();
		
		Person Person2 = context.getBean("hello", Person.class); // 매개변수로 객체를넣을때 이런식으로하면 형변환 안해줘도됨
		Person2.print();
		
		Printer printer = context.getBean("printerB", Printer.class);
		Person1.setPrinter(printer);
		Person1.print();
		
		if(Person1 == Person2) {
			System.out.println("같은 객체");
		}else
			System.out.println("다른객체");
	}

}
