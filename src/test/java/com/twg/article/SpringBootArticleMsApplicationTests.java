package com.twg.article;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.twg.article.dao.ArticleDao;
import com.twg.article.dao.UserDao;
import com.twg.article.model.Article;
import com.twg.article.model.User;


@SpringBootTest
class SpringBootArticleMsApplicationTests {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ArticleDao articleDao;

	@Test
	void contextLoads() {
		List<User> lists = userDao.selectUsers();
		for (User sysUser : lists) {
			System.out.println(sysUser);
		}
	}
	@Test
	void testArticle() {
		List<Article> list = articleDao.selectArticleList();
		for (Article article : list) {
			System.out.println(article);
		}
	}

}
