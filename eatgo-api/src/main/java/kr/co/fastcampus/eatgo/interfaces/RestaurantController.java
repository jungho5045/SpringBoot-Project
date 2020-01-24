package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController                                                                                 // @Component의 일종, Rest API를 이용하는 Rest Controller를 만든다는 어노테이션 @RestController를 선언한다.
public class RestaurantController {

    @Autowired                                                                                  // @Autowired 어노테이션을 이용하면 2번째 밑에줄처럼 객체를 선언하지 않아도 객체를 사용할 수 있다.
    private RestaurantRepository repository;
    // private RestaurantRepository repository = new RestaurantRepository();                       // domain Layer에 RestaurantRepository를 만들어 사용한다.

    @GetMapping("/restaurants")                                                                 // ui Layer로 "/restaurants"로 클라이언트가 접근을 하였을때 하위 기능과 맴핑을 시켜준다.
    public List<Restaurant> list() {                                                            // Restaurant의 목록을 얻는 List() 메서드를 선언한다.

        List<Restaurant> restaurants = repository.findAll();                                    // repository에 있는 Restaurant List를 모두 restaurants List에 저장한다.

        return restaurants;                                                                     // restaurants List를 반환한다.
    }

    @GetMapping("/restaurants/{id}")                                                            // ui Layer로 "/restaurants/{id}"로 클라이언트가 접근을 하였을때 하위 기능과 맴핑을 시켜준다.
    public Restaurant detail(@PathVariable("id") Long id) {                                     // @PathVariable로 id를 선언하고 전달받은 매개변수 id를 매칭시켜준다.

        Restaurant restaurant = repository.findById(id);                                        // repository List에서 전달받은 id로 해당 정보를 찾고 그 객체를 restaurant에 저장한다.

        return restaurant;                                                                      // restaurant 객체를 반환한다.
    }

}
