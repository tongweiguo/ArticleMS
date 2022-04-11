package com.twg.article.config;


import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * <p>主页视图配置类
 * <p>设置默认主页 或者实现WebMvcConfigurer 接口
 * @author weiguo
 */
//@Configuration //和配置spring.mvc.view冲突
public class IndexView extends WebMvcConfigurationSupport {

	/**
     * 添加主页方法
     * @param registry 主页注册器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("设置了主页");
        //设置主页
        registry.addViewController("/").setViewName("/index.jsp");
        //registry.addViewController("/").setViewName("forward:/index.jsp");
        //设置优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        //将主页注册器添加到视图控制器中
        super.addViewControllers(registry);
    }
    
    
}
