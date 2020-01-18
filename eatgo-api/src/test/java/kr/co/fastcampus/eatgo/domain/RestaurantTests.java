package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");  // 객체 생성시 생성자로 가게이름 설정

        assertThat(restaurant.getName(), is("Bob zip"));    // restaurant.getName()시 return값으로 "Bob zip"이면 true, 아니면 false
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul"); // 객체 생성 시 생성자로 가게이름, 가게주소 설정
        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));    // restaurant.getName()시 return값으로 "Bob zip in Seoul"이면 true, 아니면 false
    }

}