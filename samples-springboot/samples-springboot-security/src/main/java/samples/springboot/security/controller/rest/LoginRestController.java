package samples.springboot.security.controller.rest;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author: daibin
 * @date: 2021/7/28 4:01 下午
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class LoginRestController {

    @PostMapping("/login")
    public String login() {
        return "ok";
    }

    @PostMapping("/login/alipay")
    public String loginAlipay() throws AlipayApiException {
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPjwjl3XGhR3B+5afN0njBrcYMsi24rz5IvgpMStIdGmWFmnfAehnAOFO6dmhJOsyCwGF03Ap5LxtY8TwT2w1r1toNLfiXbABzmubWvtopbUqYvGkGsT5wsclr92GZGYN6iY9RUeHASL/2CjzQhfhx6wGRThu6S7BC1ptwsOxysyWHRnYDqySXZxWZquSaU4u8Zh6je/hGuCuZxQfduh60bhbK6MEWNWZQ6hGlZdib1qIKQiaHk2Fut1yxO1pVCMQcL+BSQ2GrG973YAwz2UMr1TKtVGqzkz6EljnhSQOgr/464Pso0IqpToB09mgdCKe6c9HPu/b1JLsjUX5KFikVAgMBAAECggEAQf9e9tYsNBn0tNH9qBjXnSq3fwS3Ek22U5jSgSpwmCKXg6l03k8D4aPDklh3LxtSA9ZPt3qlcfrfFq+oj/RHokke66ozu+FR/9O5xgXto9fz0G3l1sbLTuHdGL6c5tac5EuzUI5VO6H2ZjVkqfS9OBnzGYsQIkBDoyaoRVGBEmVrRTRP5/fBiAf8Llnl5SV9h/yTWpRyaOtcV1qBUnxxBr0C108LODKT5DXrThzJqzn9EM0u09i6ohI1Ju+l62Ldgoa4zG6U6NYHMxwZl/lDZmCieepBg4HXMLRxES9sEEitkWkHKg79+gzDwqnP/tEpgkgV83aQr+6N2Wk5p0qRDQKBgQDFovQEPeE+5+BR0/QWPIcazt/El256trBIqNcjS5RrKImtfcqs657KLGABFOqLDKVj+3mz4o8zoOiCwrJBS5vVPLHqO/Nbg82bjA3tBJKl0tYdt9qoYwUpXIA11N2vxgJiK9pMFUqbPfUw6Cyg5sjAZDMkRHAaZK57F171hGn09wKBgQC589/uoXNjF3jLUIrmrORalfBdBzPlZhwME5j2LSOsvS6MqYVpjp9yIujXk3rwgJbaZo2EZxQUspXrvWZ3USYYn3mmkHfCulfj0hHsK1WkbbPromEUqL2Bfp7ZpOg4SNu7QhhlE7EZQdgeWZCL8P/X8glPkJxmxNwPh76+6MvrUwKBgQCNNGfzv+J292SmEjGK0aQV20i1RRyCQJSi1T8g0JcyGLyFYCK2eYHyINJW84K/5kEktj+17mEyGf0Fr+V8EXE9YSgcmsi80ebNA7vPZD0CjSi+AuYO+PBvJM/VpkjdlTOOwJYvVecAE7xjPP7l1v4Ht49ykhMmSFkBSSlncTvi8QKBgQCyvi1+oErd8W33YVBjM8W9HWuSwJ2t4F0NbrkVyYYFQURBiaHoOWQivlO7BNSj4bcf8HwPzVWMH63kkIayIprQCFtTNIMp9x0MrJz1uyHzkE/mLIvM4/ZlR2VCKDXgBIjSk1IHtsoBUT2Azu8W1W3YEZvJk2QDlS9C7mT39aYuaQKBgH1C9zFCumKQQemZTs0Hlzxsi4f+1SR5PBSXdthBos2G9sSxRE6S0Sf3/2M0fA90jxLyy3R1jPT7xEpgbNCWMWd253GssFesOCuXMSp8IVyk2gfBfPAekJJrH82O5T5KZibLnfQqI54SrrnaokWjjakDMVu6cFEb401pIKTs97tX";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setPrivateKey(privateKey);
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

        AlipayUserInfoAuthRequest request = new AlipayUserInfoAuthRequest();
        request.setReturnUrl("");
        request.setBizContent("");
        AlipayUserInfoAuthResponse response = alipayClient.pageExecute(request, "get");
        if (response.isSuccess()) {
            log.info("授权登录发起成功");
        } else {
            log.warn("授权登录发起失败");
        }
        return "ok";
    }

    public static void main(String[] args) throws AlipayApiException {
//        LoginRestController login = new LoginRestController();
//        String ret = login.loginAlipay();
//        System.out.println(ret);
    }

    @PostMapping(value = "/login/callback/alipay")
    public String loginCallbackAlipay() {
        return "ok";
    }

    @PostMapping(value = "/login/callback/wx")
    public String loginCallbackWx() {
        return "ok";
    }

    @GetMapping("/currentUserInfo")
    public Authentication login(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return authentication;
    }
}
