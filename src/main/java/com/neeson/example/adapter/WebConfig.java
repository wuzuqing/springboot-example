//package com.neeson.example.adapter;
//
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/myStatic/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/myStatic/");
//        //注：ResourceUtils.CLASSPATH_URL_PREFIX就是"classpath:",如果不加这个，就会提示找不到资源
//        super.addResourceHandlers(registry);
//    }
//}