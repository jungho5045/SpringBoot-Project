package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;     // ArrayList를 사용하기 위해 import 시켜준다.
import java.util.List;          // List를 사용하기 위해 import 시켜준다.

@RestController     // Rest API를 이용하는 Rest Controller를 만든다는 어노테이션 @RestController를 선언한다.
public class RestaurantController {

    @GetMapping("/restaurants")                                                                 //
    public List<Restaurant> list() {                                                            // Restaurant의 목록을 얻는 List() 메서드를 선언한다.
        List<Restaurant> restaurants = new ArrayList<>();                                       // ArrayList생성자를 이용하여 restaurants List를 생성한다.

        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");     // Restaurant 자료형의 restaurant 객체를 생성하고, 생성시 매개변수로 1004, "Bob zip", "Seoul" 를 이용한다.

        restaurants.add(restaurant);                                                            // 생성한 restaurant 객체를 restaurants List에 add 해준다.

        return restaurants;                                                                     // restaurants List를 반환한다.
    }

    @GetMapping("/restaurants/1")
    public Restaurant detail() {
        return null;
    }

}
