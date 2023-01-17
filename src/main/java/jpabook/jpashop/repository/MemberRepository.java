package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em; // 스프링 부트 라이브러리 에서는 @PersistenceContext 대신, @Autowired도 사용 가능

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 객체를 넣음, 나중에 트랜잭션이 커밋될 때 DB에 반영
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // (타입, PK)
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList(); // JPQL 사용, from의 대상이 테이블이 아닌 엔티티
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name) // 파라미터 바인딩
                .getResultList();
    }
}
