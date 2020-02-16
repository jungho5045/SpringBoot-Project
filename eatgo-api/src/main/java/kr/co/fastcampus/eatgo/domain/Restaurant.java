package kr.co.fastcampus.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;

    @Transient  // 임시로 실행한다는 어노테이션(DB 저장 안함)
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

//    public Restaurant(String name) {    // 생성자 파라매터로 name을 받아, Restaurant class의 맴버변수 name에 저장하는 함수
//        this.name = name;
//    }

    public  Restaurant(){
        //
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(long id, String name, String address) {    // 생성자 파라매터로 name과 address를 받는다
        this.id = id;
        this.name = name;                               // 파라매터 name을 Restaurant class의 맴버변수 name에 저장하는 함수
        this.address = address;                         // 파라매터 address를 Restaurant class의 맴버변수 address에 저장하는 함수
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {       // getId() 메서드로, Restaurant class의 맴버변수 id를 리턴해 준다.
        return id;
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItem(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }

    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
