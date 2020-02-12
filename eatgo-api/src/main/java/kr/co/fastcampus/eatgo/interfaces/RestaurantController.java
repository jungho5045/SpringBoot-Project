package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController                                                                                 // @Component의 일종, Rest API를 이용하는 Rest Controller를 만든다는 어노테이션 @RestController를 선언한다.
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // private RestaurantRepository repository = new RestaurantRepository();                    // domain Layer에 RestaurantRepository를 만들어 사용한다.

    @GetMapping("/restaurants")                                                                 // ui Layer로 "/restaurants"로 클라이언트가 접근을 하였을때 하위 기능과 맴핑을 시켜준다.
    public List<Restaurant> list() {                                                            // Restaurant의 목록을 얻는 List() 메서드를 선언한다.

        List<Restaurant> restaurants = restaurantService.getRestaurants();                      // repository에 있는 Restaurant List를 모두 restaurants List에 저장한다.

        return restaurants;                                                                     // restaurants List를 반환한다.
    }

    @GetMapping("/restaurants/{id}")                                                            // ui Layer로 "/restaurants/{id}"로 클라이언트가 접근을 하였을때 하위 기능과 맴핑을 시켜준다.
    public Restaurant detail(@PathVariable("id") Long id) {                                     // @PathVariable로 id를 선언하고 전달받은 매개변수 id를 매칭시켜준다.

        Restaurant restaurant = restaurantService.getRestaurant(id);                            // 기본정보 + 메뉴정보를 한번에 돌려준다, 하나의 가게 정보를 얻을 수 있다.
        return restaurant;                                                                      // restaurant 객체를 반환한다.
    }


    @PostMapping("restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {                               // POST 요청에 따른 상태를 같이 반환해주기 위해 ResponseEntity를 반환타입으로 설정한다.
//        String name = resource.getName();
//        String address = resource.getAddress();
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = new Restaurant(name, address);
        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

}
