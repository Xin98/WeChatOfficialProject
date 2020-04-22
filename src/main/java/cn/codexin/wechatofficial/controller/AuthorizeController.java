package cn.codexin.wechatofficial.controller;

import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by xinGao 2020/4/22
 */
@RestController
public class AuthorizeController {

    @Value("${wechat.token}")
    private String token;

    @GetMapping("/wx")
    public String checkSignature(@RequestParam(name = "signature", required = false) String signature,
                                 @RequestParam(name = "timestamp", required = false) String timestamp,
                                 @RequestParam(name = "nonce", required = false) String nonce,
                                 @RequestParam(name = "echostr", required = false) String echostr
                                 ) throws NoSuchAlgorithmException {
        //首先解密微信服务器发过来的请求，确认该请求有效
        try {
            Objects.requireNonNull(signature);
            Objects.requireNonNull(timestamp);
            Objects.requireNonNull(nonce);
            Objects.requireNonNull(echostr);
        }catch (NullPointerException e){
            return "无效的请求";
        }
        String[] array = new String[]{token, timestamp, nonce};
        List<String> list = new ArrayList<>(Arrays.asList(array));
        //默认字典排序
        String str = list.stream().sorted().collect(Collectors.joining());
        //Sha1 加密
        MessageDigest sha1 = MessageDigest.getInstance("SHA");
        byte[] digest = sha1.digest(str.getBytes());
        String realMessage = HexUtils.toHexString(digest);
        if(realMessage.equals(signature))
            return echostr;
        else return "无效的请求";
    }
}
