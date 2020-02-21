package kr.co.fastcampus.eatgo.domain;

public class RestaurantNotFondException extends RuntimeException {


    public RestaurantNotFondException(long id) {
        super("Could not find restaurant " + id);
    }
}
