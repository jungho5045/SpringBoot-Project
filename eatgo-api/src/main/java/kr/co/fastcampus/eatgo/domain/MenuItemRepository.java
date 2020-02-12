package kr.co.fastcampus.eatgo.domain;

        import org.springframework.data.repository.CrudRepository;
        import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {    // extends CrudRepository 해주면 MenuItemRepository에 대한 구현 없이 사용할 수 있따.
    List<MenuItem> findAllByRestaurantId(Long retaurantid);
}
