package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // ID가 없다 = 새로 생성한 객체(JPA에 저장하기 전까지 ID값이 없음)
            em.persist(item);
        } else { // ID값이 있다 = 이미 DB에 등록되어 있는 것을 가져온 것
            em.merge(item); // UPDATE랑 비슷함, 강제 업데이트
            // merge는 변경 감지와는 달리, 모든 속성이 변경된다.
            // 따라서 실무에선 병합 시 값이 없으면 null로 업데이트 될 가능성이 있어 위험하다!
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id); // 1건만 찾을 경우는 find() 사용 가능
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
