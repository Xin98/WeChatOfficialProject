package cn.codexin.wechatofficial.wechat;

import cn.codexin.wechatofficial.dto.AccessTokenDTO;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by xinGao 2020/4/22
 */
@Component
public class WeChatProvider {

    @Value("${wechat.grant-type}")
    private String grant_type;

    @Value("${wechat.app-id}")
    private String appId;

    @Value("${wechat.app-secret}")
    private String secret;

    public AccessTokenDTO getAccessToken(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+ appId +"&secret="+secret).build();
        try (Response response = client.newCall(request).execute()) {
            //错误情况还未处理
            return JSON.parseObject(response.body().string(), AccessTokenDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
