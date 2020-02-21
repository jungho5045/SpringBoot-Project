package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.RestaurantNotFondException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class RestaurantErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFondException.class)
    public String handleNotFound() {
        return "{}";
    }
}
