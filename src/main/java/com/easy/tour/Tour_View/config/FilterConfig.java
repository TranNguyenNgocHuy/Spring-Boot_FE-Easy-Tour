package com.easy.tour.Tour_View.config;

import com.easy.tour.Tour_View.utils.JwtTokenFilterUtil;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;

import javax.xml.xpath.XPathExpressionException;

@Configuration
public class FilterConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean<JwtTokenFilterUtil> jwtTokenFilter() {
        FilterRegistrationBean<JwtTokenFilterUtil> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenFilterUtil());
        registrationBean.addUrlPatterns("/admin/v1/*"); // Filter sẽ được áp dụng cho mọi request
        registrationBean.addUrlPatterns("/resources/*"); // take css file
        return registrationBean;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Set maximum file size (here 10MB)
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        // Set maximum request size (here 20MB)
        factory.setMaxRequestSize(DataSize.ofMegabytes(20));
        return factory.createMultipartConfig();
    }
}