package org.example.config;

import org.example.interceptor.RequestInterceptor;
import org.example.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Register the locale resolver bean. This is used to resolve the locale for the
     * application.
     * @return the locale resolver bean
     */
    @Bean
    public LocaleResolver localeResolver() {
       return new SessionLocaleResolver();
   }
    /**
     * Register the view controller. This is used to map a URL to a view name.
     * @param registry the registry to add view controllers to
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName(ViewNames.HOME);
    }

    /**
     * Register the interceptor that we have created. Similar to reigstering the view
     * controller.
     * @param registry the registry to add interceptors to
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
        // will use the default parameter name "locale", setParamNome to change it
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}
