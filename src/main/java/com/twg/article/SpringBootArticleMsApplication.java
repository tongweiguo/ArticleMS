package com.twg.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.twg.article.dao")
public class SpringBootArticleMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootArticleMsApplication.class, args);
	}

}
