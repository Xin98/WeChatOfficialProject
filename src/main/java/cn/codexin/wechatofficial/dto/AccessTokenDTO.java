package cn.codexin.wechatofficial.dto;

import lombok.Data;

/**
 * Created by xinGao 2020/4/22
 */
@Data
public class AccessTokenDTO {
    private String access_token;
    private Long expires_in;
}
