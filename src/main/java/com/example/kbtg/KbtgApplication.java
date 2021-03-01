package com.example.kbtg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KbtgApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(KbtgApplication.class, args);
		String[] beans = context.getBeanDefinitionNames();
		for (String bean : beans) {
			System.out.println(bean);
		}
		System.out.println(context.getBeanDefinitionCount());
		User user1 = context.getBean(User.class);
		user1.setName("Name ABC");
		System.out.println(user1.getName()); // 1
		User user2 = context.getBean(User.class);
		user2.setName("User 2");
		System.out.println(user2.getName()); // 2
		System.out.println(user1.getName()); // 3
	}

}
