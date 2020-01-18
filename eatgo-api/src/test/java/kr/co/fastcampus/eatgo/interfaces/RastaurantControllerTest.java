package kr.co.fastcampus.eatgo.interfaces;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)          // JUnit4 @RunWith 대신 JUnit5 @ExtendWith 사용
// @RunWith(SpringRunner.class)            // Spring을 이용하여 해당 Test를 실행 할 수 있게 @RunWith 어노테이션을 추가한다.
@WebMvcTest(RastaurantController.class) // RestaurantController.class를 Test한다고 명시할 수 있다.
class RastaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))  // restaurant에 대한 GET 요청
            .andExpect(status(). isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
            .andExpect(content().string(
                    containsString("\"name\":\"Bob zip\"")
            ));
    }

}