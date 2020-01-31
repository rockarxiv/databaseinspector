package com.task.databaseinspector;

import com.task.databaseinspector.interceptor.ConnectionIdParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
public class DatabaseinspectorApplication implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(DatabaseinspectorApplication.class, args);
    }

    @Autowired
    ConfigurableListableBeanFactory factory;

    @Autowired
    private ConnectionIdParamInterceptor connectionIdParamInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(connectionIdParamInterceptor).addPathPatterns("/**").excludePathPatterns("/connection**");
    }

    @PostConstruct
    public void addScope() {
        factory.registerScope("thread", new SimpleThreadScope());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
