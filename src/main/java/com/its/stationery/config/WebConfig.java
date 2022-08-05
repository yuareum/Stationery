package com.its.stationery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    private String connectPath = "/upload/**";
    private String resourcePath = "file:///C:/springboot_img/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }

    public static class PagingConst {
        public static final int PAGE_LIMIT = 12; // 한페이지에 보여줄 글 갯수
        public static final int BLOCK_LIMIT = 3; // 한화면에 보여줄 페이지 갯수
    }
}
