package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component                                                                                          // RestaurntRepository를 Spring이 관리 할 수 있도록 @Component 어노테이션을 추가한다.
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();                                       // ArrayList생성자를 이용하여 restaurants List를 생성한다.

    public RestaurantRepositoryImpl() {                                                                 // RestaurantRepository 메서드를 선언한다.
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));               // 생성한 restaurant 객체를 restaurants List에 add 해준다.
        restaurants.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
    }

    @Override
    public List<Restaurant> findAll() {                                                             // restaurants List를 반환하는 findAll() 메서드이다.
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {                                                           // id를 매개변수로 restaurants List에 해당하는 자료를 찾고 반환하는 findById() 메서드이다.
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234L);
        restaurants.add(restaurant);
        return restaurant;
    }
}
