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
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id); // 1건만 찾을 경우는 find() 사용 가능
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
