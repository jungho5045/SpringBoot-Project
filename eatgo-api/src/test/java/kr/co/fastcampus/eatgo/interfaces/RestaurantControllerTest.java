package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)          // JUnit4 @RunWith 대신 JUnit5 @ExtendWith 사용, GET 요청시 어디에 요청할지 정한다(SpringExtention에 요청한다.)
// @RunWith(SpringRunner.class)             // Spring을 이용하여 해당 Test를 실행 할 수 있게 @RunWith 어노테이션을 추가한다.
@WebMvcTest(RestaurantController.class)     // RestaurantController.class를 Test한다고 명시할 수 있다.
class RestaurantControllerTest {

    @Autowired                                       // mvc객체를 만들지 않아도 알아서? SpringBoot에서 알아서 넣어줄 수 있도록 @Autowired 어노테이션을 선언한다.
    private MockMvc mvc;                             // mvc객체를 이용할 수 있게 해준다.

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {            // perform에서 예외가 발생할 수 있어서 throws Exception을 해준다.
        List<Restaurant> restaurants = new ArrayList<>();                                       // Mock Object(가짜 객체) 부분
        restaurants.add(new Restaurant(1004L, "JOKER House", "Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))  // mvc객체를 이용하여 perform을 한다. perform은 restaurant에 대한 GET 요청이다.
            .andExpect(status(). isOk())             // 또한 요청한 GET에 대해서 요청의 성공유무를 위해 andExpect(status(). isOk())를 한다.
                .andExpect(content().string(         // 가게 이름이 나오도록 content()를 사용한다.
                        containsString("\"id\":1004")       //
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")  //
            ));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = new Restaurant(1004L, "JOKER House", "Seoul");
        restaurant1.addMenuItem(new MenuItem("Kimchi"));

        Restaurant restaurant2 = new Restaurant(2020L, "Cyber Food", "Seoul");

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")       //
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(containsString("Kimchi")));     // MenuItem 추가 부분

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")       //
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }

    @Test
    public void create() throws Exception {
//        Restaurant restaurant = new Restaurant(1234L, "BeRyong", "Seoul");

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

}