package cn.codexin.wechatofficial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xinGao 2020/4/22
 */


@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        System.out.println("123");
        return "hello my index page";
    }
}
