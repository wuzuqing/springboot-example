package com.neeson.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源
     * 此处可使用 properties 文件配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
                .addVersionStrategy(new ContentVersionStrategy(), "/**");
        registry.addResourceHandler("static/**")
                .addResourceLocations("classpath:/static/",getRootDir())
                .setCachePeriod(2592000).resourceChain(true)
                .addResolver(versionResourceResolver);
    }

    private String getRootDir(){
        boolean isLinux = "Linux".equals(getOSName());
        if (isLinux){
            return "/usr";
        }
        return "f:\\IdeaProjects\\springboot_updownload\\src\\main\\resources\\";
    }
    /**
     * 直接获取系统名称 区分
     */

    public static String getOSName() {
        String osName = System.getProperties().getProperty("os.name");
        if (osName.equals("Linux")) {
            System.out.println("linux");
        } else {
            System.out.println("other");
        }

        return osName;

    }


}