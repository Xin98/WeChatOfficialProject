package cn.codexin.wechatofficial.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by xinGao 2020/4/22
 */

@Configuration
//使用该注解会拦截静态文件，这个以后再剖析源码!
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AccessTokenInterceptor accessTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor).addPathPatterns("/**");
    }
}
