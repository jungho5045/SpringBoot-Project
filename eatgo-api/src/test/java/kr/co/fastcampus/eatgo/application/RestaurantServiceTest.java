package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp(){
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();

        restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    public void getRestaurnat(){
        Restaurant restaurnat = restaurantService.getRestaurant(1004L);

        assertThat(restaurnat.getId(), is(1004L));
    }

}