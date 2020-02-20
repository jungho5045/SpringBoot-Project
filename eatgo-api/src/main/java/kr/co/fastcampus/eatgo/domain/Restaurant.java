package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter                     // 속성에 대한 Gettern 메서드들을 생성해준다.
@Builder                    // 객체 생성 시 build pattern을 사용할 수 있게 해준다.(@AllArgsConstructor와 같이 사용)
@NoArgsConstructor          // 파라미터가 없는 기본 생성자를 생성해준다.
@AllArgsConstructor         // 모든 필드 값을 파라미터로 받는 생성자를 생성해준다.
public class Restaurant {

    // 속성
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient  // 임시로 실행한다는 어노테이션(DB 저장 안함)
    private List<MenuItem> menuItems;

    public String getInformation() {    // getInformation() 메서드로, Restaurant class의 맴버변수를 이용해 name + " in " + address를 리턴해 준다.
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
