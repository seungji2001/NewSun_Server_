package com.newsun.Newsun.controller;

import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.service.OAuth2Service;
import com.newsun.Newsun.type.ELoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final OAuth2Service oAuth2Service;


    //callback url 정보 담아오기
    @GetMapping("/google")
    public ResponseDto<Map<String,String>> getGoogleRedirectUrl(){
        Map<String, String> map = new HashMap<>();
        map.put("url", oAuth2Service.getRedirectUrl(ELoginProvider.GOOGLE));
        return ResponseDto.ok(map);
    }

    @GetMapping("/google/callback")
    public void getGoogleAccessToken(String code, HttpServletResponse response) throws IOException {
        String accessToken = oAuth2Service.getAccessToken(code, ELoginProvider.GOOGLE);
        oAuth2Service.login(response, accessToken, ELoginProvider.GOOGLE);
    }

    @GetMapping("/kakao")
    public ResponseDto<Map<String,String>> getKakaoRedirectUrl(){
        Map<String, String> map = new HashMap<>();
        map.put("url", oAuth2Service.getRedirectUrl(ELoginProvider.KAKAO));
        return ResponseDto.ok(map);
    }

//    @GetMapping("/naver/callback")
//    public void getNaverAccessToken(String code, HttpServletResponse response) throws IOException {
//        String accessToken = loginService.getKakaoAccessToken(code);
//        System.out.println(accessToken);
//
//        // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
//        HashMap<String, Object> userInfo = loginService.getUserInfo(accessToken);
//        System.out.println("login Controller : " + userInfo);
//
//        PostLoginRes postLoginRes = null;
//
//        // 만일, DB에 해당 email을 가지는 유저가 없으면 회원가입 시키고 유저 식별자와 JWT 반환
//        // 현재 카카오 유저의 전화번호를 받아올 권한이 없어서 테스트를 하지 못함.
//        if(loginProvider.checkEmail(String.valueOf(userInfo.get("email"))) == 0) {
//            //PostLoginRes postLoginRes = 해당 서비스;
//            return new BaseResponse<>(postLoginRes);
//        } else {
//            // 아니면 기존 유저의 로그인으로 판단하고 유저 식별자와 JWT 반환
//            postLoginRes = loginProvider.getUserInfo(String.valueOf(userInfo.get("email")));
//            return new BaseResponse<>(postLoginRes);
//        }
//    }
}
