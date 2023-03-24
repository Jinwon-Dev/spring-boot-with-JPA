package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue // 기본키 지정, 이 둘은 세트로!
    @Column(name = "member_id") // 속성명 지정
    private Long id;

    private String name;

    @Embedded // 임베디드 타입 사용
    private Address address;

    @OneToMany(mappedBy = "member") // Order 테이블에 있는 member에 의해 매핑된 거울일 뿐!
    private List<Order> orders = new ArrayList<>();
}
