package cn.codexin.wechatofficial.service;

import cn.codexin.wechatofficial.dto.AccessTokenDTO;
import cn.codexin.wechatofficial.mapper.AccessTokenMapper;
import cn.codexin.wechatofficial.model.AccessToken;
import cn.codexin.wechatofficial.model.AccessTokenExample;
import cn.codexin.wechatofficial.wechat.WeChatProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xinGao 2020/4/22
 */

/**
 * 用于获取access_token的 service
 * 会判断当前token是否过期，过期则更新
 */
@Service
public class AccessTokenService {

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Autowired
    private WeChatProvider weChatProvider;

    public String getAccessTokenByAppId(String appId) {
        AccessTokenExample example = new AccessTokenExample();
        example.createCriteria().andAppIdEqualTo(appId);
        //mybatis list 不会返回空对象
        List<AccessToken> accessTokens = accessTokenMapper.selectByExample(example);
        AccessToken token = new AccessToken();
        if(accessTokens.size() != 0){
            token = accessTokens.get(0);
        }
        else{
            //保证接下来会去刷新token
            token.setGmtTokenExpire(System.currentTimeMillis());
            token.setAppId(appId);
        }
        if(token.getGmtTokenExpire() < System.currentTimeMillis()){//如果token过期了
            //重新获取token
            AccessTokenDTO accessToken = weChatProvider.getAccessToken();
            token.setAccessToken(accessToken.getAccess_token());
            //过期时间设置为 总时间 - 5分钟
            token.setGmtTokenExpire(accessToken.getExpires_in() * 1000 - 300 * 1000 + System.currentTimeMillis());
            //更新数据库
            if(token.getId() == null){ //没有就插入
                accessTokenMapper.insert(token);
            }
            else{ //有就更新
                accessTokenMapper.updateByPrimaryKey(token);
            }
            System.out.println("成功更新了token");
        }
        return token.getAccessToken();
    }
}
