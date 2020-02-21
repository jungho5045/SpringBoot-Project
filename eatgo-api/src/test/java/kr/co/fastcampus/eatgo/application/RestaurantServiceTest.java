package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(of(restaurant));
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());

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
    public void getRestaurnatWithExisted(){                                             // 존재하는 Restaurant 정보를 얻는 Test 메서드
        Restaurant restaurnat = restaurantService.getRestaurant(1004L);             // RestaurantService에서 id를 매개변수로 가게 상세 정보를 가져오는 코드

        assertThat(restaurnat.getId(), is(1004L));                               // RestaurantService에서 가져온 가게 정보가 맞는지 확인하는 코드

        MenuItem menuItem = restaurnat.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));                               // RestaurantService에서 가져온 가게 정보가 맞는지 확인하는 코드
    }

    // @Test(expected = RestaurantNotFondException.class)
    public void getRestaurnatWithNotExisted(){                                         // 존재하지 않는 Restaurant 정보를 얻는 Test 메서드
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L))
                .willReturn(java.util.Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}