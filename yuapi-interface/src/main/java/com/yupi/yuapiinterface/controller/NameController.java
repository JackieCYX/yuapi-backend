package com.yupi.yuapiinterface.controller;

import org.springframework.web.bind.annotation.*;
import com.yupi.yuapiclientsdk.model.User;
import com.yupi.yuapiclientsdk.utils.SignUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 名称API
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
        System.out.println(request.getHeader("yupi"));
        return "GET 你的名字是：" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        if (!accessKey.equals("yupi")) {
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        String serverSign = SignUtils.genSign(body, "abcdefgh");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        String result = "POST 用户名字是：" + user.getUsername();
        // 调用成功后次数+1
        return result;
    }
}
