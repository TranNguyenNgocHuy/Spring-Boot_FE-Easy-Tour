package com.easy.tour.Tour_View.config;

import com.easy.tour.Tour_View.utils.JwtTokenFilterUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.xml.xpath.XPathExpressionException;

@Configuration
public class FilterConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public FilterRegistrationBean<JwtTokenFilterUtil> jwtTokenFilter() {
//        FilterRegistrationBean<JwtTokenFilterUtil> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtTokenFilterUtil());
//        registrationBean.addUrlPatterns("/admin/v1/*"); // Filter sẽ được áp dụng cho mọi request
//        registrationBean.addUrlPatterns("/resources/*"); // take css file
//        return registrationBean;
//    }
}
