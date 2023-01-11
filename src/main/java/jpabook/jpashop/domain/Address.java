package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 내장될 수 있다
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // JPA 스펙 상 만들어둔다.
    }

    public Address(String city, String street, String zipcode) { // 값 타입은 변경 불가능한 클래스를 만들자
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
