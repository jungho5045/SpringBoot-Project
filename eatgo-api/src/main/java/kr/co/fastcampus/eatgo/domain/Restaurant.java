package kr.co.fastcampus.eatgo.domain;

public class Restaurant {

    private final Long id;
    private final String name;
    private final String address;

//    public Restaurant(String name) {    // 생성자 파라매터로 name을 받아, Restaurant class의 맴버변수 name에 저장하는 함수
//        this.name = name;
//    }

    public Restaurant(long id, String name, String address) {    // 생성자 파라매터로 name과 address를 받는다
        this.id = id;
        this.name = name;                               // 파라매터 name을 Restaurant class의 맴버변수 name에 저장하는 함수
        this.address = address;                         // 파라매터 address를 Restaurant class의 맴버변수 address에 저장하는 함수
    }

    public String getName() {   // getName() 메서드로, Restaurant class의 맴버변수 name를 리턴해 준다.
        return name;
    }

    public String getAddress() {    // getName() 메서드로, Restaurant class의 맴버변수 address를 리턴해 준다.
        return address;
    }

    public String getInformation() {    // getInformation() 메서드로, Restaurant class의 맴버변수를 이용해 name + " in " + address를 리턴해 준다.
        return name + " in " + address;
    }

    public Long getId() {
        return id;
    }
}
