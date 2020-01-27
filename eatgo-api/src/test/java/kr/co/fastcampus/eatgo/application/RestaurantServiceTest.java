package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    // spring 자체에서 객체를 생성하기 때문에 의존관계를 주입할 수 없어 직접 RestaurantService가 Repository를 연결 할 수 있게 만들어 준다.
    @BeforeEach                                                                          // 모든 TestCode가 실행되기 전에 반드시 한번씩 실행시키는 어노테이션이다./Junit5에서는 Junit4에서 사용하는 @Before 대신하여 @BeforeEach를 사용한다.
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository);                // RestaurantService에서 사용하는 Repository를 직접 넣어준다.
    }

    private void mockRestaurantRepository() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(restaurant);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));

        given(menuItemRepository.findAllByRestaurantId(1004L)).
                willReturn(menuItems);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurnat(){                                                        // Restaurant 정보를 얻는 Test 메서드
        Restaurant restaurnat = restaurantService.getRestaurant(1004L);             // RestaurantService에서 id를 매개변수로 가게 상세 정보를 가져오는 코드

        assertThat(restaurnat.getId(), is(1004L));                               // RestaurantService에서 가져온 가게 정보가 맞는지 확인하는 코드

        MenuItem menuItem = restaurnat.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));                               // RestaurantService에서 가져온 가게 정보가 맞는지 확인하는 코드
    }

}