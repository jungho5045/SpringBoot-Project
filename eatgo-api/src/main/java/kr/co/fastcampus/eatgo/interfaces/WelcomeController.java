package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Web에서 접속 가능한 controller anotaion 명시
public class WelcomeController {

    @GetMapping("/")    // http에서 접속에 필요한 기본 4가지 중 get() 사용
    public String hello(){  // Web 접속 시 보이는 부분
        return "Hello, world!!!";
    }

}