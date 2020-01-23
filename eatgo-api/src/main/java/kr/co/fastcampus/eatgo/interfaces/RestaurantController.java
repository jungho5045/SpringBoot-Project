package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // Rest API를 이용하는 Rest Controller를 만든다는 어노테이션 @RestController를 선언한다.
public class RestaurantController {

    private RestaurantRepository repository = new RestaurantRepository();

    @GetMapping("/restaurants")                                                                 //
    public List<Restaurant> list() {                                                            // Restaurant의 목록을 얻는 List() 메서드를 선언한다.

        List<Restaurant> restaurants = repository.findAll();

        return restaurants;                                                                     // restaurants List를 반환한다.
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = repository.findById(id);

        return restaurant;
    }

}
