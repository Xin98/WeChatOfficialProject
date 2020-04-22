package cn.codexin.wechatofficial.interceptor;

import cn.codexin.wechatofficial.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xinGao 2020/4/22
 */
@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private AccessTokenService accessTokenService;

    @Value("${wechat.app-id}")
    private String appId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = accessTokenService.getAccessTokenByAppId(appId);
        request.getSession().setAttribute("token", accessToken);
        System.out.println(accessToken);
        return true;
    }
}
