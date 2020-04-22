package cn.codexin.wechatofficial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.codexin.wechatofficial.mapper")
@SpringBootApplication
public class WechatofficialApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatofficialApplication.class, args);
    }

}
